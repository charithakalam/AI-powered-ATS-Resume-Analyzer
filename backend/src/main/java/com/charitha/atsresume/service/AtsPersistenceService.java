package com.charitha.atsresume.service;

import com.charitha.atsresume.dto.*;
import com.charitha.atsresume.model.AtsResultEntity;
import com.charitha.atsresume.repository.AtsResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtsPersistenceService {

    @Autowired
    private AtsResultRepository atsResultRepository;

    public AtsResultEntity saveResult(
            String resumeId,
            WeightedSkillMatchResult skillResult,
            ExperienceMatchResult experienceResult,
            FinalAtsResult finalResult,
            AtsExplanationResult explanationResult,
            Long userId) {

        String explanationText =
                String.join(" | ", explanationResult.getExplanation());

        AtsResultEntity entity = new AtsResultEntity(
                resumeId,
                skillResult.getWeightedMatchPercentage(),      // ✅ REAL skill score
                experienceResult.getExperienceMatchPercentage(), // ✅ REAL experience
                finalResult.getFinalAtsScore(),
                explanationText
        );

        entity.setUserId(userId);

        return atsResultRepository.save(entity);
    }
}
