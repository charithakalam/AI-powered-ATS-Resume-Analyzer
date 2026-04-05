package com.charitha.atsresume.dto;

public class FinalAtsResult {

    private double skillScore;
    private double experienceScore;
    private double finalAtsScore;

    public FinalAtsResult(double skillScore,
                          double experienceScore,
                          double finalAtsScore) {
        this.skillScore = skillScore;
        this.experienceScore = experienceScore;
        this.finalAtsScore = finalAtsScore;
    }

    public double getSkillScore() {
        return skillScore;
    }

    public double getExperienceScore() {
        return experienceScore;
    }

    public double getFinalAtsScore() {
        return finalAtsScore;
    }
}
