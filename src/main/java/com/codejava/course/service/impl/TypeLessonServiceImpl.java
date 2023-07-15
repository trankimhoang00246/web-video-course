package com.codejava.course.service.impl;

import com.codejava.course.model.entity.course.TypeLesson;
import com.codejava.course.model.form.TypeLessonForm;
import com.codejava.course.repository.TypeLessonRepository;
import com.codejava.course.service.TypeLessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeLessonServiceImpl implements TypeLessonService {
    private final TypeLessonRepository typeLessonRepository;

    @Override
    public TypeLesson save(TypeLessonForm typeLessonForm) {
        var typeLesson = TypeLesson.builder()
                .name(typeLessonForm.getName())
                .build();
        return typeLessonRepository.save(typeLesson);
    }

    @Override
    public List<TypeLesson> getAll() {
        return typeLessonRepository.findAll();
    }
}
