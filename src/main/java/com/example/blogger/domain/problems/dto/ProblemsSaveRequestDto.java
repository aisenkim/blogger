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

    @Builder
    public ProblemsSaveRequestDto(String title, String url, String topic, String pattern, String difficulty) {
        this.title = title;
        this.url = url;
        this.topic = topic;
        this.pattern = pattern;
        this.difficulty = difficulty;
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
