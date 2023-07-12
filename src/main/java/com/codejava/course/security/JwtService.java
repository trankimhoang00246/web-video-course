package com.codejava.course.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.codejava.course.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;

@Component
public class JwtService {
    private final JwtProperties jwtProperties;
    private final UserDetailsService userDetailsService;

    public JwtService(JwtProperties jwtProperties, UserDetailsService userDetailsService) {
        this.jwtProperties = jwtProperties;
        this.userDetailsService = userDetailsService;
    }

    public String generateAccessToken(Authentication auth){
        return jwtProperties.getPrefix() + JWT.create()  //"Bearer + token" create

                .withExpiresAt(Instant.now().plusMillis(jwtProperties.getAccessExpiresAt())) //để thiết lập thời gian hết hạn của token

                .withSubject(auth.getName()) // để thiết lập subject (chủ đề) của token. Ở đây là tên người dùng auth

                // để thêm các claim (tuyên bố) vào token. Ở đây thêm một claim "role" có giá trị là quyền của người dùng.
                .withClaim("role", auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse(null))

                .sign(Algorithm.HMAC512(jwtProperties.getAccessSecret())); //để ký và tạo token
    }

    public String generateRefreshToken(Authentication auth){
        return jwtProperties.getPrefix() + JWT.create()
                .withExpiresAt(Instant.now().plusMillis(jwtProperties.getRefreshExpiresAt()))
                .withIssuedAt(Instant.now()) //xác định thời gian JWT được tạo; chúng ta có thể sử dụng điều này để xác định tuổi của JWT
                .withSubject(auth.getName())
                .sign(Algorithm.HMAC512(jwtProperties.getRefreshSecret()));
    }

    public boolean validateAccessToken(String token){
            // 1, Le bon secret a été utilisé (et le meme algo)
            // 2, pas expiré

            //Sử dụng thuật toán mã hóa (HMAC512) và chuỗi bí mật (secret) đúng để giải mã token. Nếu sai sẽ báo lỗi.
            DecodedJWT jwt = JWT.require( Algorithm.HMAC512(jwtProperties.getAccessSecret()) )
                    .acceptExpiresAt( jwtProperties.getAccessExpiresAt() ) //Kiểm tra xem token có hết hạn hay không
                    .withClaimPresence("sub") //Đây là các yêu cầu về các trường dữ liệu bắt buộc (claims) trong JWT.
                                                 // JWT sẽ chỉ được chấp nhận nếu nó chứa các claim sub và role.
                    .withClaimPresence("role")
                    .build()
                    //: Đây là phương thức để xác thực JWT. token là chuỗi JWT được truyền vào để kiểm tra tính hợp lệ của nó.
                    // Nếu JWT hợp lệ, phương thức này sẽ trả về một đối tượng DecodedJWT chứa thông tin của JWT đã xác thực.
                    // Nếu JWT không hợp lệ, phương thức này sẽ ném ra một ngoại lệ
                    .verify( token );

            // 3, généré a partir d'un user existant
            String username = jwt.getSubject();
            User user = (User) userDetailsService.loadUserByUsername(username);
            if( !user.isEnabled() )
                return false;

            // (4, Les roles ne sont plus bon) Verifier les roles n'est pas conventionnel
            String tokenRole = jwt.getClaim("role").asString();
            return Objects.equals(user.getRole(),  tokenRole);
    }

    public boolean validateRefreshToken(String token){
            JWT.require( Algorithm.HMAC512(jwtProperties.getRefreshSecret()) )
                    .acceptExpiresAt( jwtProperties.getRefreshExpiresAt() )
                    .withClaimPresence("sub")
                    .withClaimPresence("iat")
                    .build()
                    .verify( token );
            return true;
    }

    public String extractToken(HttpServletRequest request){
        String authHeader = request.getHeader( jwtProperties.getAuthHeader() );
        if(authHeader == null || !authHeader.startsWith( jwtProperties.getPrefix() ))
            return null;
        return authHeader.replaceFirst(jwtProperties.getPrefix(), "" );
    }

    public Authentication createAuthentication(String token){
        DecodedJWT jwt = JWT.decode(token);

        String username = jwt.getSubject();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities()
        );
    }
}
