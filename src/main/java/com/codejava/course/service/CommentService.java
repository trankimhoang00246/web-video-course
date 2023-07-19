package com.codejava.course.service;

import com.codejava.course.model.dto.CommentDto;
import com.codejava.course.model.form.CommentForm;

import java.util.List;

public interface CommentService {

    CommentDto save(CommentForm commentForm);

    List<CommentDto> getAll();

    List<CommentDto> getAllChildOneLevel(long parentId);

    List<CommentDto> getAllParent();

}
