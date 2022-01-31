package com.example.blogger.domain.problems.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProblemDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("pattern")
    private String pattern;

    @JsonProperty("difficulty")
    private String difficulty;

    @JsonProperty("source")
    private String source;

    @JsonProperty("solved")
    private Integer solved;

    @JsonProperty("review")
    private Boolean review;

    @Builder
    public UpdateProblemDto(Long id, String title, String url, String topic, String pattern, String difficulty, String source, Integer solved, Boolean review) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.topic = topic;
        this.pattern = pattern;
        this.difficulty = difficulty;
        this.source = source;
        this.solved = solved;
        this.review = review;
    }
}
