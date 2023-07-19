package com.codejava.course.service.impl;

import com.codejava.course.model.dto.CommentDto;
import com.codejava.course.model.entity.Comment;
import com.codejava.course.model.entity.Node;
import com.codejava.course.model.entity.CategoryBase;
import com.codejava.course.model.form.CommentForm;
import com.codejava.course.repository.CategoryBaseRepository;
import com.codejava.course.repository.CommentRepository;
import com.codejava.course.repository.NodeRepository;
import com.codejava.course.service.CommentService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public record CommentServiceImpl(
        CommentRepository commentRepository,
        CategoryBaseRepository categoryBaseRepository,
        NodeRepository nodeRepository
) implements CommentService {


    @Override
    public CommentDto save(CommentForm commentForm) {

        if(commentForm.getParentId().equals(0L)) {
            CategoryBase categoryBase = categoryBaseRepository.findByName("lesson")
                    .orElseThrow(() ->
                            new NotFoundException("Note found base with name lesson"));

            var nodeParent = Node.builder()
                    .parentId(0L)
                    .lefts(1L)
                    .rights(2L)
                    .build();
            var comment = Comment.builder()
                    .node(nodeParent)
                    .userId(commentForm.getUserId())
                    .categoryBase(categoryBase)
                    .entityId(commentForm.getEntityId())
                    .content(commentForm.getContent())
                    .build();
            nodeParent.setComment(comment);
            Comment comment1 = commentRepository.save(comment);
            comment1.setIdCommentParent(comment1.getNode().getId());

            return Comment.toDto(commentRepository.save(comment1));

        } else {
            Node node = nodeRepository.findById(commentForm.getParentId())
                    .orElseThrow(() ->
                            new NotFoundException("Node not found with id "+ commentForm.getParentId()));

            CategoryBase categoryBase = categoryBaseRepository.findByName("lesson")
                    .orElseThrow(() ->
                            new NotFoundException("Note found base with name lesson"));

            var nodeParent = Node.builder()
                    .parentId(commentForm.getParentId())
                    .lefts(node.getRights())
                    .rights(node.getRights() + 1L)
                    .build();
            var comment = Comment.builder()
                    .node(nodeParent)
                    .userId(commentForm.getUserId())
                    .categoryBase(categoryBase)
                    .entityId(commentForm.getEntityId())
                    .content(commentForm.getContent())
                    .idCommentParent(node.getComment().getIdCommentParent())
                    .build();
            nodeParent.setComment(comment);

            updateNodeSave(node.getComment().getIdCommentParent(), commentForm.getEntityId(), categoryBase, node.getRights());

            return Comment.toDto(commentRepository.save(comment));
        }
    }

    @Override
    public List<CommentDto> getAll() {
        return commentRepository.findAll()
                .stream()
                .map(comment -> Comment.toDto(comment))
                .collect(Collectors.toList());
    }

    //lấy con 1 cấp cửa conment
    @Override
    public List<CommentDto> getAllChildOneLevel(long parentId) {
        return nodeRepository.findByParentId(parentId)
                .stream()
                .map(node -> Comment.toDto(node.getComment()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getAllParent() {
        return null;
    }

    private void updateNodeSave(Long idCommentParent, Long entityId, CategoryBase categoryBase, Long value) {
        nodeRepository.findByComment_IdCommentParentAndComment_EntityIdAndComment_CategoryBaseAndRightsGreaterThanEqual(idCommentParent, entityId, categoryBase, value)
                .forEach(node ->
                {
                    node.setRights(node.getRights()+2L);
                    nodeRepository.save(node);
                });

        nodeRepository.findByComment_IdCommentParentAndComment_EntityIdAndComment_CategoryBaseAndLeftsGreaterThanEqual(idCommentParent, entityId, categoryBase, value)
                .forEach(node ->
                {
                    node.setLefts(node.getLefts()+2L);
                    nodeRepository.save(node);
                });
    }
}
