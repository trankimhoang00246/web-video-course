package com.codejava.course.repository;

import com.codejava.course.model.entity.course.lesson.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository  extends JpaRepository<Video, Long> {
}
