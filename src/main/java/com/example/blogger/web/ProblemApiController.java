package com.example.blogger.web;

import com.example.blogger.domain.problems.Problems;
import com.example.blogger.domain.problems.dto.UpdateProblemDto;
import com.example.blogger.services.problems.ProblemService;
import com.example.blogger.domain.problems.dto.ProblemsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/problems")
@RestController
public class ProblemApiController {

    private final ProblemService problemService;

    /**
     * Save problem
     * @param requestDto - dto for saving problem
     * @return saved id
     */
    @PostMapping
    public ResponseEntity<Long> save(@RequestBody ProblemsSaveRequestDto requestDto) {
        return ResponseEntity.ok().body(problemService.save(requestDto));
    }

    /**
     * Get problems by category
     * @param category - 1) review 2) solved
     * @return List of problems in provided category
     */
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

    /**
     * Updates problem with whatever is provided by id
     * @param updateDto - fields that don't need update are null
     * @return - updated problem
     */
    @PutMapping
    public ResponseEntity<Problems> updateProblem(@RequestBody UpdateProblemDto updateDto) {
        return ResponseEntity.ok().body(problemService.updateProblem(updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteProblem(@PathVariable Long id) {
        return ResponseEntity.ok().body(problemService.deleteProblem(id));
    }
}
