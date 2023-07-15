package com.codejava.course.controller;

import com.codejava.course.model.dto.AuthDto;
import com.codejava.course.model.form.LoginForm;
import com.codejava.course.model.form.RegisterForm;
import com.codejava.course.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public record AuthController(AuthService authService) {

    @PostMapping("/login")
    @Operation(summary = "Log in")
    public AuthDto login(@RequestBody LoginForm form){
        return authService.login(form);
    }

    @PostMapping("/register")
    @Operation(summary = "Register")
    public String login(@RequestBody RegisterForm form){
        return authService.register(form);
    }

    @GetMapping("/refresh")
    @Operation(summary = "Refresh token")
    public AuthDto refreshToken(@RequestHeader("X-Refresh-Token") String refreshToken){
        return authService.refreshJWT(refreshToken);
    }
}
