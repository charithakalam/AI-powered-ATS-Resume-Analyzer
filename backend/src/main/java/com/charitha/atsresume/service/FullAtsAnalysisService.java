package com.charitha.atsresume.service;

import com.charitha.atsresume.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FullAtsAnalysisService {

    @Autowired
    private WeightedSkillMatchingService weightedSkillMatchingService;

    @Autowired
    private ExperienceMatchingService experienceMatchingService;

    @Autowired
    private FinalAtsScoringService finalAtsScoringService;

    @Autowired
    private AtsExplainabilityService atsExplainabilityService;

    @Autowired
    private AtsPersistenceService atsPersistenceService;

    public FullAtsAnalysisResult analyze(FullAtsAnalysisRequest request) {

        // 1️⃣ Skill analysis
        WeightedSkillMatchResult skillResult =
                weightedSkillMatchingService.matchSkills(
                        request.getResumeSkills(),
                        request.getMandatorySkills(),
                        request.getOptionalSkills()
                );

        // 2️⃣ Experience analysis
        ExperienceMatchResult experienceResult =
                experienceMatchingService.matchExperience(
                        request.getResumeExperience(),
                        request.getJdExperience()
                );

        // 3️⃣ Final ATS score
        FinalAtsResult finalResult =
                finalAtsScoringService.calculateFinalScore(
                        skillResult.getWeightedMatchPercentage(),
                        experienceResult.getExperienceMatchPercentage()
                );
        AtsExplanationResult explanation =
                atsExplainabilityService.explain(
                        skillResult,
                        experienceResult,
                        finalResult,
                        request.getResumeText()
                );

        atsPersistenceService.saveResult(
                request.getResumeId(),
                skillResult,
                experienceResult,
                finalResult,
                explanation,
                request.getUserId()
        );

        return new FullAtsAnalysisResult(
                skillResult,
                experienceResult,
                finalResult,
                explanation
        );

    }
}
