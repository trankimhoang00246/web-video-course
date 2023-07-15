package com.codejava.course.service;

import com.codejava.course.model.dto.CourseDto;
import com.codejava.course.model.form.CourseForm;

import java.util.List;

public interface CourseService {
    CourseDto save(CourseForm courseForm);

    List<CourseDto> getAll();
}
