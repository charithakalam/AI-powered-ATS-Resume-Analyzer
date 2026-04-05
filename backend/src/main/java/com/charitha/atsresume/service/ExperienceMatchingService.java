package com.charitha.atsresume.service;

import com.charitha.atsresume.dto.ExperienceMatchResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ExperienceMatchingService {

    public ExperienceMatchResult matchExperience(
            Integer resumeExperience,
            Integer jdExperience) {

        // ✅ handle null safely
        if (resumeExperience == null || jdExperience == null) {
            return new ExperienceMatchResult(
                    Set.of(),
                    Set.of(),
                    0.0
            );
        }

        // ✅ VERY IMPORTANT FIX
        if (jdExperience == 0) {
            return new ExperienceMatchResult(
                    Set.of(),
                    Set.of(),
                    100.0
            );
        }

        double percentage;

        if (resumeExperience >= jdExperience) {
            percentage = 100.0;
        } else {
            percentage = (resumeExperience * 100.0) / jdExperience;
        }

        return new ExperienceMatchResult(
                Set.of(),
                Set.of(),
                Math.round(percentage)
        );
    }
}