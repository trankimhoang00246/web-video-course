package com.codejava.course.repository;

import com.codejava.course.model.entity.course.lesson.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository  extends JpaRepository<Text, Long> {
}
