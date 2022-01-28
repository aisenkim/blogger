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

    @Builder
    public UpdateProblemDto(String title, String url, String topic, String pattern, String difficulty) {
        this.title = title;
        this.url = url;
        this.topic = topic;
        this.pattern = pattern;
        this.difficulty = difficulty;
    }
}
