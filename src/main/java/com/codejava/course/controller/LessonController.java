package com.codejava.course.controller;


import com.codejava.course.model.dto.LessonDto;
import com.codejava.course.model.form.lesson.LessonTextForm;
import com.codejava.course.model.form.lesson.LessonVideoForm;
import com.codejava.course.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/lesson")
public record LessonController(LessonService lessonService) {

    @PostMapping("/save/video")
    @Operation(summary = "Save lesson type video")
    public ResponseEntity saveLessonVideo(@RequestBody LessonVideoForm lessonVideoForm) {
        return new ResponseEntity<>(lessonService.save(lessonVideoForm), HttpStatus.CREATED);
    }

    @PostMapping("/save/text")
    @Operation(summary = "Save lesson type text")
    public ResponseEntity saveLessonText(@RequestBody LessonTextForm lessonTextForm) {
        return new ResponseEntity<>(lessonService.save(lessonTextForm), HttpStatus.CREATED);
    }

    @GetMapping("/")
    @Operation(summary = "Get all")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(lessonService.getAll(), HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all lesson of course")
    public ResponseEntity getAllByCourseId(@RequestParam("course-id") Long courseId) {
        return new ResponseEntity<>(lessonService.getAllByCourseId(courseId), HttpStatus.OK);
    }
}
