package com.example.blogger.domain.problems.dto;

import com.example.blogger.domain.problems.Problems;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProblemsSaveRequestDto {
    private String title;
    private String url;
    private String topic;
    private String pattern;
    private String difficulty;
    private Boolean review;
    private Integer solved;
    private String source;

    @Builder
    public ProblemsSaveRequestDto(String title, String url, String topic, String pattern, String difficulty, Boolean review, Integer solved, String source) {
        this.title = title;
        this.url = url;
        this.topic = topic;
        this.pattern = pattern;
        this.difficulty = difficulty;
        this.review = review;
        this.solved = solved;
        this.source = source;
    }

    public Problems toEntity() {
        return Problems.builder()
                .title(title)
                .url(url)
                .topic(topic)
                .pattern(pattern)
                .difficulty(difficulty)
                .build();
    }
}
