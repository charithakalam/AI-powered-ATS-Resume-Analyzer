package com.charitha.atsresume.dto;

import java.util.Set;

public class ExperienceMatchResult {

    private Set<String> matchedExperienceSkills;
    private Set<String> missingExperienceSkills;
    private double experienceMatchPercentage;

    public ExperienceMatchResult(Set<String> matchedExperienceSkills,
                                 Set<String> missingExperienceSkills,
                                 double experienceMatchPercentage) {
        this.matchedExperienceSkills = matchedExperienceSkills;
        this.missingExperienceSkills = missingExperienceSkills;
        this.experienceMatchPercentage = experienceMatchPercentage;
    }

    public Set<String> getMatchedExperienceSkills() {
        return matchedExperienceSkills;
    }

    public Set<String> getMissingExperienceSkills() {
        return missingExperienceSkills;
    }

    public double getExperienceMatchPercentage() {
        return experienceMatchPercentage;
    }
}
