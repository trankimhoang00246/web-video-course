package com.codejava.course.controller;

import com.codejava.course.model.form.CourseForm;
import com.codejava.course.service.CourseService;
import com.codejava.course.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/course")
public record CourseController(CourseService courseService) {

    @PostMapping("/save")
    @Operation(summary = "Save course")
    public ResponseEntity save(@RequestBody CourseForm courseForm) {
        return new ResponseEntity(courseService.save(courseForm), HttpStatus.CREATED);
    }

    @GetMapping("/")
    @Operation(summary = "Get all")
    public ResponseEntity getAll() {
        return new ResponseEntity(courseService.getAll(), HttpStatus.OK);
    }
}
