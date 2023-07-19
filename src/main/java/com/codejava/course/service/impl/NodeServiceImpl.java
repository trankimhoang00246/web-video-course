package com.codejava.course.service.impl;

import com.codejava.course.model.entity.Node;
import com.codejava.course.repository.NodeRepository;
import com.codejava.course.service.NodeService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

@Service
public record NodeServiceImpl(
        NodeRepository nodeRepository
) implements NodeService {

    Node getNewNode(Long parentId, Long categoryId, Long entityId) {
        if(parentId.equals(0L)) {
            return Node.builder()
                    .parentId(parentId)
                    .build();
        } else {
            Node parentNode = nodeRepository.findById(parentId)
                    .orElseThrow(() ->
                            new NotFoundException("Not found node with id " + parentId));

            var newNode = Node.builder()
                    .parentId(parentId)
                    .lefts(parentNode.getRights())
                    .rights(parentNode.getRights() + 1L)
                    .build();
            this.updateNodeWhenAdd();

            return newNode;
        }
    }

    void delete() {

    }

    void updateNodeWhenAdd() {

    }

}
