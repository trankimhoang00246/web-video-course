package com.codejava.course.model.entity.course.lesson;

import com.codejava.course.model.entity.course.AbstractLesson;
import com.codejava.course.model.entity.CategoryBase;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "video")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video extends AbstractLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    /* TODO:
            @Lob // Large Object
            @Column(name = "data", columnDefinition = "LONGBLOB")
            private byte[] data;
    */
    private String url;
    private int duration;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
