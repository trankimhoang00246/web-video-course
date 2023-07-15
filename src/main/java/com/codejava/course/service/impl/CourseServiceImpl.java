package com.codejava.course.service.impl;

import com.codejava.course.model.dto.CourseDto;
import com.codejava.course.model.entity.course.Course;
import com.codejava.course.model.entity.discount.DiscountStrategy;
import com.codejava.course.model.entity.discount.base.HalfDiscountImpl;
import com.codejava.course.model.form.CourseForm;
import com.codejava.course.repository.CourseRepository;
import com.codejava.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    @Override
    public CourseDto save(CourseForm courseForm) {
        var course = Course.builder()
                .name(courseForm.getName())
                .requirement(courseForm.getRequirement())
                .description(courseForm.getDescription())
                .price(courseForm.getPrice())
                .build();
        DiscountStrategy discountStrategy = new HalfDiscountImpl();
        course.setPriceAfterDiscount(discountStrategy);

        Course courseSave = courseRepository.save(course);

        return modelMapper.map(courseSave, CourseDto.class);
    }

    @Override
    public List<CourseDto> getAll() {
        return courseRepository.findAll()
                .stream()
                .map(course ->
                        modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }
}
