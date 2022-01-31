package com.example.blogger.domain.problems;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemsRepository extends JpaRepository<Problems, Long> {
    List<Problems> findByReviewIs(Boolean review);

    List<Problems> findBySolvedGreaterThan(Integer solved);

    Problems findByTitle(String title);
}
