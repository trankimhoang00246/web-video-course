package com.codejava.course.service;

import com.codejava.course.model.dto.LessonDto;
import com.codejava.course.model.form.lesson.Base;

import java.util.List;

public interface LessonService {

    String save(Base lessonForm);

    List<LessonDto> getAll();

    List<LessonDto> getAllByCourseId(Long courseId);
}
