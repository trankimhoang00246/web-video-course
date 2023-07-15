package com.codejava.course.service;

import com.codejava.course.model.entity.course.TypeLesson;
import com.codejava.course.model.form.TypeLessonForm;

import java.util.List;

public interface TypeLessonService {
    TypeLesson save(TypeLessonForm typeLessonForm);

    List<TypeLesson> getAll();
}
