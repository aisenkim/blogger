package com.example.blogger.services.problems;

import com.example.blogger.domain.problems.Problems;
import com.example.blogger.domain.problems.dto.ProblemsSaveRequestDto;
import com.example.blogger.domain.problems.dto.UpdateProblemDto;

import java.util.List;

public interface ProblemService {

    // SAVE LEETCODE PROBLEM
    Long save(ProblemsSaveRequestDto requestDto);

    // GET LEETCODE PROBLEM
    List<Problems> getProblems(String sort);

    List<Problems> getReviewProblems();

    List<Problems> getSolvedProblems();

    // UPDATE A PROBLEM
    Problems updateProblem(UpdateProblemDto problemDto);

}
