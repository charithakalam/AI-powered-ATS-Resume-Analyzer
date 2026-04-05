package com.charitha.atsresume.dto;

import java.util.Set;

public class WeightedSkillMatchResult {

    private Set<String> matchedMandatorySkills;
    private Set<String> missingMandatorySkills;
    private Set<String> matchedOptionalSkills;
    private Set<String> missingOptionalSkills;
    private double weightedMatchPercentage;

    public WeightedSkillMatchResult(
            Set<String> matchedMandatorySkills,
            Set<String> missingMandatorySkills,
            Set<String> matchedOptionalSkills,
            Set<String> missingOptionalSkills,
            double weightedMatchPercentage) {

        this.matchedMandatorySkills = matchedMandatorySkills;
        this.missingMandatorySkills = missingMandatorySkills;
        this.matchedOptionalSkills = matchedOptionalSkills;
        this.missingOptionalSkills = missingOptionalSkills;
        this.weightedMatchPercentage = weightedMatchPercentage;
    }

    public Set<String> getMatchedMandatorySkills() {
        return matchedMandatorySkills;
    }

    public Set<String> getMissingMandatorySkills() {
        return missingMandatorySkills;
    }

    public Set<String> getMatchedOptionalSkills() {
        return matchedOptionalSkills;
    }

    public Set<String> getMissingOptionalSkills() {
        return missingOptionalSkills;
    }

    public double getWeightedMatchPercentage() {
        return weightedMatchPercentage;
    }
}
