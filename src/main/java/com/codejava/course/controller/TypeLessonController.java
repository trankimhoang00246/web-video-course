package com.codejava.course.controller;

import com.codejava.course.model.form.TypeLessonForm;
import com.codejava.course.service.TypeLessonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/type-lesson")
public record TypeLessonController(TypeLessonService typeLessonService) {

    @PostMapping("/save")
    @Operation(summary = "Save type lesson")
    public ResponseEntity save(@RequestBody TypeLessonForm typeLessonForm) {
        return new ResponseEntity<>(typeLessonService.save(typeLessonForm), HttpStatus.CREATED);
    }

    @GetMapping("/")
    @Operation(summary = "Get all")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(typeLessonService.getAll(), HttpStatus.CREATED);
    }
}
