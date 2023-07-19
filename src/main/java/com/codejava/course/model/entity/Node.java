package com.codejava.course.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nodes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Node {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;
    private Long lefts;
    private Long rights;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "node")
    private Comment comment;
}
