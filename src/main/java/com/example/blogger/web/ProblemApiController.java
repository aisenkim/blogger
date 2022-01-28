package com.example.blogger.web;

import com.example.blogger.domain.problems.Problems;
import com.example.blogger.services.problems.ProblemService;
import com.example.blogger.domain.problems.dto.ProblemsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/problems")
@RestController
public class ProblemApiController {

    private final ProblemService problemService;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody ProblemsSaveRequestDto requestDto) {
        return ResponseEntity.ok().body(problemService.save(requestDto));
    }

    @GetMapping("/category")
    public ResponseEntity<List<Problems>> getProblemsByCategory(@RequestParam String category) {
        return ResponseEntity.ok().body(problemService.getProblemsByCategory(category));
    }

    /**
     * Get problems sorting by 1. difficulty 2. pattern 3. type
     * @param sort - sort type
     * @return - List of sorted problems
     */
    @GetMapping
    public ResponseEntity<List<Problems>> getProblems(@RequestParam String sort) {
        return ResponseEntity.ok().body(problemService.getProblems(sort));
    }

}
