package com.codejava.course.model.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LessonDto {
    private Long id;
    private String title;
    private int number;
    private Long typeId;
    private Long lessonId;
    private Long courseId;
}
