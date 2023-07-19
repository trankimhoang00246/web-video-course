package com.codejava.course.controller;

import com.codejava.course.model.form.CategoryBaseForm;
import com.codejava.course.model.form.CommentForm;
import com.codejava.course.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/comment")
public record CommentController(CommentService commentService) {

    @PostMapping("/save")
    @Operation(summary = "Save type lesson")
    public ResponseEntity save(@RequestBody CommentForm commentForm) {
        return new ResponseEntity<>(commentService.save(commentForm), HttpStatus.CREATED);
    }

    @GetMapping("/")
    @Operation(summary = "Get all")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(commentService.getAll(), HttpStatus.OK);
    }

    @GetMapping()
    @Operation(summary = "Get all child one level")
    public ResponseEntity getAllChildOneLevel(@RequestParam("parent-id") long parentId) {
        return new ResponseEntity<>(commentService.getAllChildOneLevel(parentId), HttpStatus.OK);
    }
}
