package com.codejava.course.model.dto;

import com.codejava.course.model.entity.CategoryBase;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentDto {
    private Long id;
    private Long idCommentParent;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;

    private Long nodeId;
    private Long parentId;
    private Long left;
    private Long right;

    private CategoryBase categoryBase;
    private Long entityId;
}
