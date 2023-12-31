package com.codejava.course.model.entity.course.lesson;

import com.codejava.course.model.entity.course.AbstractLesson;
import com.codejava.course.model.entity.CategoryBase;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "text")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Text extends AbstractLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    private CategoryBase categoryBase;

    @Override
    public Long getLessonId() {
        return id;
    }

    @Override
    public Long getTypeId() {
        return categoryBase.getId();
    }

    @Override
    public String getTitle() {
        return title;
    }
}
