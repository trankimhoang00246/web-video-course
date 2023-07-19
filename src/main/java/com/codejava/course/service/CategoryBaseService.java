package com.codejava.course.service;

import com.codejava.course.model.dto.CategoryBaseDto;
import com.codejava.course.model.form.CategoryBaseForm;

import java.util.List;

public interface CategoryBaseService {
    CategoryBaseDto save(CategoryBaseForm categoryBaseForm);

    List<CategoryBaseDto> getAll();
}
