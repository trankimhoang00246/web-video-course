package com.codejava.course.model.entity;

import com.codejava.course.model.BaseEntity;
import com.codejava.course.model.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_comment_parent")
    private Long idCommentParent; // == id node parent 0

    @Column(name = "user_id")
    private Long userId;
    private String content;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "node_id")
    private Node node;

    //comment phai biet no la comment cua entity nao
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryBase categoryBase;

    @Column(name = "entity_id")
    private Long entityId;

    public static CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .idCommentParent(comment.getIdCommentParent())
                .userId(comment.getUserId())
                .createdAt(comment.getCreatedAt())
                .lastModifiedAt(comment.getLastModifiedAt())
                .nodeId(comment.getNode().getId())
                .parentId(comment.getNode().getParentId())
                .left(comment.getNode().getLefts())
                .right(comment.getNode().getRights())
                .categoryBase(comment.getCategoryBase())
                .entityId(comment.entityId)
                .content(comment.getContent())
                .build();
    }
}
