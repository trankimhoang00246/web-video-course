package com.codejava.course.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseDto {
    private Long id;
    private String name;
    private String requirement;
    private String description;
    private BigDecimal price;
    private BigDecimal salePrice;
}
