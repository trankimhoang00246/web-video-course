package com.codejava.course.model.entity;

import com.codejava.course.model.dto.CategoryBaseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "type_lesson")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public static CategoryBaseDto toDto(CategoryBase categoryBase) {
        return new CategoryBaseDto(categoryBase.getId(), categoryBase.getName());
    }
}
