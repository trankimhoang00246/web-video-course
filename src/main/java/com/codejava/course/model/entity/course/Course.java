package com.codejava.course.model.entity.course;

import com.codejava.course.model.entity.User;
import com.codejava.course.model.entity.discount.DiscountStrategy;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String requirement;
    private String description;
    private BigDecimal price;
    @Column(name = "sale_price")
    private BigDecimal salePrice; //sau khi giam gia

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userSet;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "course")
    private List<Lesson> lessonSet;

    public void setPriceAfterDiscount(DiscountStrategy discountStrategy) {
        this.salePrice = discountStrategy.doDiscount(this.price);
    }
}
