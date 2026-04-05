package com.charitha.atsresume.dto;

import java.util.Set;

public class SkillMatchResult {

    private Set<String> matchedSkills;
    private Set<String> missingSkills;
    private double matchPercentage;

    public SkillMatchResult(Set<String> matchedSkills,
                            Set<String> missingSkills,
                            double matchPercentage) {
        this.matchedSkills = matchedSkills;
        this.missingSkills = missingSkills;
        this.matchPercentage = matchPercentage;
    }

    public Set<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(Set<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public Set<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(Set<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }
}
