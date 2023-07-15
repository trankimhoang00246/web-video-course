package com.codejava.course.repository;

import com.codejava.course.model.entity.course.TypeLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeLessonRepository extends JpaRepository<TypeLesson, Long> {
}
