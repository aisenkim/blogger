package com.example.blogger.services.problems;

import com.example.blogger.domain.problems.Problems;
import com.example.blogger.domain.problems.ProblemsRepository;
import com.example.blogger.domain.problems.dto.ProblemsSaveRequestDto;
import com.example.blogger.domain.problems.dto.UpdateProblemDto;
import com.example.blogger.domain.problems.enums.ProblemSource;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ProblemServiceImplementation implements ProblemService {

    private final ProblemsRepository problemsRepository;

    @Override
    public Long save(ProblemsSaveRequestDto requestDto) {
        return problemsRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    public List<Problems> getProblems(String sort) {
        // SORT: DIFFICULTY(DEFAULT), PATTERN, TYPE
        if (!sort.equals("difficulty") && !sort.equals("pattern") && !sort.equals("type"))
            sort = "difficulty";
        return problemsRepository.findAll(Sort.by(Sort.Direction.DESC, sort));
    }

    /**
     * GET problems to review (true) or solved (greater than 0)
     *
     * @param category - review || solved
     * @return - List of problems or null
     */
    @Override
    public List<Problems> getProblemsByCategory(String category) {
        List<Problems> problems = null;
        if (category.equalsIgnoreCase("review")) {
            problems = problemsRepository.findByReviewIs(true);
        } else if (category.equalsIgnoreCase("solved")) {
            problems = problemsRepository.findBySolvedGreaterThan(0);
        }
        return problems;
    }

    @Override
    public Problems updateProblem(UpdateProblemDto problemDto) {
        Problems problemToUpdate = problemsRepository.findById(problemDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("No such problem exists"));

        if (problemDto.getTitle() != null && problemDto.getTitle().length() > 0 && !Objects.equals(problemDto.getTitle(), problemToUpdate.getTitle())) {
            problemToUpdate.setTitle(problemDto.getTitle());
        }

        if (problemDto.getUrl() != null && !Objects.equals(problemDto.getUrl(), problemToUpdate.getUrl())) {
            problemToUpdate.setUrl(problemDto.getUrl());
        }

        if (problemDto.getTopic() != null && !Objects.equals(problemDto.getTopic(), problemToUpdate.getTopic())) {
            problemToUpdate.setTopic(problemDto.getTopic());
        }

        if (problemDto.getPattern() != null && !Objects.equals(problemDto.getPattern(), problemToUpdate.getPattern())) {
            problemToUpdate.setPattern(problemDto.getPattern());
        }

        if (problemDto.getDifficulty() != null && !Objects.equals(problemDto.getDifficulty(), problemToUpdate.getDifficulty())) {
            problemToUpdate.setDifficulty(problemDto.getDifficulty());
        }

        if (problemDto.getSource() != null && ProblemSource.isInEnum(problemDto.getSource())) {
            problemToUpdate.setSource(ProblemSource.parse(problemDto.getSource()));
        }

        if (problemDto.getSolved() != null && !Objects.equals(problemDto.getSolved(), problemToUpdate.getSolved())) {
            problemToUpdate.setSolved(problemDto.getSolved());
        }

        if (problemDto.getReview() != null && !Objects.equals(problemDto.getReview(), problemToUpdate.getReview())) {
            problemToUpdate.setReview(problemDto.getReview());
        }

        return problemToUpdate;
    }

    @Override
    public Long deleteProblem(Long id) {
        Problems problem = problemsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("The problem by the id: " + id + " doesn't exist"));
        Long deleteProblemId = problem.getId();

        problemsRepository.delete(problem);

        return deleteProblemId;
    }
}
