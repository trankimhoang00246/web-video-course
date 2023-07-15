package com.codejava.course.repository;

import com.codejava.course.model.entity.course.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository  extends JpaRepository<Lesson, Long> {
    List<Lesson> findByCourse_Id(Long courseId);
}
