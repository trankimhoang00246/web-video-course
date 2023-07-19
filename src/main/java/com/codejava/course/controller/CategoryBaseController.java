package com.codejava.course.controller;

import com.codejava.course.model.form.CategoryBaseForm;
import com.codejava.course.service.CategoryBaseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/category")
public record CategoryBaseController(CategoryBaseService categoryBaseService) {

    @PostMapping("/save")
    @Operation(summary = "Save type lesson")
    public ResponseEntity save(@RequestBody CategoryBaseForm categoryBaseForm) {
        return new ResponseEntity<>(categoryBaseService.save(categoryBaseForm), HttpStatus.CREATED);
    }

    @GetMapping("/")
    @Operation(summary = "Get all")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(categoryBaseService.getAll(), HttpStatus.CREATED);
    }
}
