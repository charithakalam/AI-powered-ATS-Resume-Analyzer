package com.charitha.atsresume.dto;

import java.util.List;
import java.util.Map;

public class FullAtsAnalysisRequest {

    private List<String> resumeSkills;
    private List<String> mandatorySkills;
    private List<String> optionalSkills;

    private Integer resumeExperience;
    private Integer jdExperience;
    private String resumeId;
    private String resumeText;
    private Long userId;

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

    public Integer getResumeExperience() {
        return resumeExperience;
    }

    public void setResumeExperience(Integer resumeExperience) {
        this.resumeExperience = resumeExperience;
    }

    public Integer getJdExperience() {
        return jdExperience;
    }

    public void setJdExperience(Integer jdExperience) {
        this.jdExperience = jdExperience;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
