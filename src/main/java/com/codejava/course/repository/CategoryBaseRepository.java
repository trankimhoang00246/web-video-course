package com.codejava.course.repository;

import com.codejava.course.model.entity.CategoryBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryBaseRepository extends JpaRepository<CategoryBase, Long> {

    Optional<CategoryBase> findByName(String name);
}
