package com.codejava.course.model.dto;

import lombok.Data;

@Data
public class CategoryBaseDto {
    private Long id;
    private String name;

    public CategoryBaseDto(Long id, String name) {
        this.id=id;
        this.name=name;
    }
}
