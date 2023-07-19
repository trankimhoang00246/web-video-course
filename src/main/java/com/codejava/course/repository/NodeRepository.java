package com.codejava.course.repository;

import com.codejava.course.model.entity.Comment;
import com.codejava.course.model.entity.Node;
import com.codejava.course.model.entity.CategoryBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node, Long> {
    List<Node> findByComment_IdCommentParentAndComment_EntityIdAndComment_CategoryBaseAndRightsGreaterThanEqual(Long idCommentParent, Long entityId, CategoryBase categoryBase, Long right);

    List<Node> findByComment_IdCommentParentAndComment_EntityIdAndComment_CategoryBaseAndLeftsGreaterThanEqual(Long idCommentParent, Long entityId, CategoryBase categoryBase, Long left);

    List<Node> findByParentId(long parentId);

}
