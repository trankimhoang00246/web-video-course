package com.codejava.course.model.form;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CourseForm {
    private String name;
    private String requirement;
    private String description;
    private BigDecimal price;
}
