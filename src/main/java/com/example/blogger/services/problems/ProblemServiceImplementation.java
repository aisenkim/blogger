package com.example.blogger.services.problems;

import com.example.blogger.domain.problems.Problems;
import com.example.blogger.domain.problems.ProblemsRepository;
import com.example.blogger.domain.problems.dto.ProblemsSaveRequestDto;
import com.example.blogger.domain.problems.dto.UpdateProblemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
        if(!sort.equals("difficulty") && !sort.equals("pattern") && !sort.equals("type"))
            sort = "difficulty";
        return problemsRepository.findAll(Sort.by(Sort.Direction.DESC, sort));
    }

    @Override
    public List<Problems> getReviewProblems() {
        return null;
    }

    @Override
    public List<Problems> getSolvedProblems() {
        return null;
    }


    @Override
    public Problems updateProblem(UpdateProblemDto problemDto) {
        return null;
    }
}
