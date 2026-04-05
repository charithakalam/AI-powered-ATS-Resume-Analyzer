package com.charitha.atsresume.dto;

import java.util.List;

public class WeightedResumeAnalysisRequest {

    private List<String> resumeSkills;
    private List<String> mandatorySkills;
    private List<String> optionalSkills;

    public List<String> getResumeSkills() {
        return resumeSkills;
    }

    public void setResumeSkills(List<String> resumeSkills) {
        this.resumeSkills = resumeSkills;
    }

    public List<String> getMandatorySkills() {
        return mandatorySkills;
    }

    public void setMandatorySkills(List<String> mandatorySkills) {
        this.mandatorySkills = mandatorySkills;
    }

    public List<String> getOptionalSkills() {
        return optionalSkills;
    }

    public void setOptionalSkills(List<String> optionalSkills) {
        this.optionalSkills = optionalSkills;
    }
}
