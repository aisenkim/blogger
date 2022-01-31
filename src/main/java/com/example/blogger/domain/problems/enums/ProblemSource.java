package com.example.blogger.domain.problems.enums;

import java.util.Arrays;

public enum ProblemSource {
    LEETCODE, ALGOEXPERT, HACKERRANK;

    public static boolean isInEnum(String value) {
        for (ProblemSource ps : ProblemSource.values()) {
            if(ps.name().equals(value))
                return true;
        }
        return false;
    }

    public static ProblemSource parse(String value) {
        for (ProblemSource ps : ProblemSource.values()) {
            if(ps.name().equals(value))
                return ps;
        }
        return null;
    }
}
