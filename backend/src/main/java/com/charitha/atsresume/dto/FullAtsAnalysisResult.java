package com.charitha.atsresume.dto;

public class FullAtsAnalysisResult {

    private WeightedSkillMatchResult skillAnalysis;
    private ExperienceMatchResult experienceAnalysis;
    private FinalAtsResult finalResult;
    private AtsExplanationResult explanation;

    public FullAtsAnalysisResult(
            WeightedSkillMatchResult skillAnalysis,
            ExperienceMatchResult experienceAnalysis,
            FinalAtsResult finalResult,
            AtsExplanationResult explanation) {

        this.skillAnalysis = skillAnalysis;
        this.experienceAnalysis = experienceAnalysis;
        this.finalResult = finalResult;
        this.explanation = explanation;
    }

    public AtsExplanationResult getExplanation() {
        return explanation;
    }

    public WeightedSkillMatchResult getSkillAnalysis() {
        return skillAnalysis;
    }

    public ExperienceMatchResult getExperienceAnalysis() {
        return experienceAnalysis;
    }

    public FinalAtsResult getFinalResult() {
        return finalResult;
    }
}
