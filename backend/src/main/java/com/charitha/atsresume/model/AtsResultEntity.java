package com.charitha.atsresume.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ats_results")
public class AtsResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resumeId;

    private double skillScore;
    private double experienceScore;
    private double finalAtsScore;
    private Long userId;

    @Column(length = 2000)
    private String explanation;

    private LocalDateTime createdAt;

    public AtsResultEntity() {}

    public AtsResultEntity(String resumeId,
                           double skillScore,
                           double experienceScore,
                           double finalAtsScore,
                           String explanation) {
        this.resumeId = resumeId;
        this.skillScore = skillScore;
        this.experienceScore = experienceScore;
        this.finalAtsScore = finalAtsScore;
        this.explanation = explanation;
        this.createdAt = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }

    public String getResumeId() {
        return resumeId;
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

    public String getExplanation() {
        return explanation;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
