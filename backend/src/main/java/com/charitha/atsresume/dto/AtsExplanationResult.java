package com.charitha.atsresume.dto;

import java.util.List;

public class AtsExplanationResult {

    private double finalAtsScore;
    private List<String> explanation;

    public AtsExplanationResult(double finalAtsScore, List<String> explanation) {
        this.finalAtsScore = finalAtsScore;
        this.explanation = explanation;
    }

    public double getFinalAtsScore() {
        return finalAtsScore;
    }

    public List<String> getExplanation() {
        return explanation;
    }
}
