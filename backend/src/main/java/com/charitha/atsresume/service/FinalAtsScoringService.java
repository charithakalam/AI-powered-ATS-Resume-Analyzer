package com.charitha.atsresume.service;

import com.charitha.atsresume.dto.FinalAtsResult;
import org.springframework.stereotype.Service;

@Service
public class FinalAtsScoringService {

    private static final double SKILL_WEIGHT = 0.7;
    private static final double EXPERIENCE_WEIGHT = 0.3;

    public FinalAtsResult calculateFinalScore(
            double weightedSkillScore,
            double experienceScore) {

        double finalScore =
                (weightedSkillScore * SKILL_WEIGHT) +
                        (experienceScore * EXPERIENCE_WEIGHT);

        return new FinalAtsResult(
                weightedSkillScore,
                experienceScore,
                Math.round(finalScore)
        );
    }
}
