package com.codejava.course.model.entity.course;

import com.codejava.course.model.dto.LessonDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lesson")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int number;
    @Column(name = "type_id")
    private Long typeId;
    @Column(name = "lesson_id")
    private Long lessonId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    public void setLesson(LessonStrategy lessonStrategy) {
        this.lessonId=lessonStrategy.getLessonId();
        this.typeId=lessonStrategy.getTypeId();
    }

    public static LessonDto toDto(Lesson lesson) {
        return LessonDto.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .number(lesson.getNumber())
                .typeId(lesson.getTypeId())
                .lessonId(lesson.getLessonId())
                .courseId(lesson.getCourse().getId())
                .build();
    }
}
