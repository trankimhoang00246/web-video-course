package com.codejava.course.service.impl;

import com.codejava.course.model.dto.CategoryBaseDto;
import com.codejava.course.model.entity.CategoryBase;
import com.codejava.course.model.form.CategoryBaseForm;
import com.codejava.course.repository.CategoryBaseRepository;
import com.codejava.course.service.CategoryBaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryBaseServiceImpl implements CategoryBaseService {
    private final CategoryBaseRepository categoryBaseRepository;

    @Override
    public CategoryBaseDto save(CategoryBaseForm categoryBaseForm) {
        var typeLesson = CategoryBase.builder()
                .name(categoryBaseForm.getName())
                .build();
        return CategoryBase.toDto(categoryBaseRepository.save(typeLesson));
    }

    @Override
    public List<CategoryBaseDto> getAll() {
        return categoryBaseRepository.findAll()
                .stream()
                .map(CategoryBase::toDto)
                .collect(Collectors.toList());
    }
}
