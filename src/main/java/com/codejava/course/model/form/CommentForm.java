package com.codejava.course.model.form;

import lombok.Data;

@Data
public class CommentForm {
    private Long userId;
    private String content;
    private Long parentId;

    private Long categoryBaseId;
    private Long entityId;
}
