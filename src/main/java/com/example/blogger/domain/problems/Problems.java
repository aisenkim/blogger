package com.example.blogger.domain.problems;

import com.example.blogger.domain.BaseTimeEntity;
import com.example.blogger.domain.problems.enums.ProblemSource;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Problems extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String url;

    @Column
    private String topic;

    @Column
    private String pattern;

    @Column
    private String difficulty;

    @Column
    @Enumerated(EnumType.STRING)
    private ProblemSource source; // Source of the problem (LEETCODE, HACKERRANK, ALGOEXPERT)

    @Column
    private Integer solved; // Number of times solved

    @Column
    private Boolean review; // If problem needs to be reviewed or not

    @Builder
    public Problems(String title, String url, String topic, String pattern, String difficulty, ProblemSource source, Integer solved, Boolean review) {
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
