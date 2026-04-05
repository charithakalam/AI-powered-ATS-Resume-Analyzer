package com.charitha.atsresume.service;

import com.charitha.atsresume.dto.WeightedSkillMatchResult;
import com.charitha.atsresume.util.SkillSynonymUtil;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class WeightedSkillMatchingService {

    private static final double MANDATORY_WEIGHT = 0.7;
    private static final double OPTIONAL_WEIGHT = 0.3;

    public WeightedSkillMatchResult matchSkills(
            List<String> resumeSkills,
            List<String> mandatorySkills,
            List<String> optionalSkills) {

        // 🔒 SAFETY CHECK (VERY IMPORTANT)
        if (resumeSkills == null || resumeSkills.isEmpty()) {
            return new WeightedSkillMatchResult(
                    Set.of(),
                    Set.of(),
                    Set.of(),
                    Set.of(),
                    0.0
            );
        }

        // (Optional but recommended)
        if (mandatorySkills == null) mandatorySkills = List.of();
        if (optionalSkills == null) optionalSkills = List.of();

        Set<String> resumeSet = resumeSkills.stream()
                .map(SkillSynonymUtil::normalizeSkill)
                .collect(Collectors.toSet());

        Set<String> mandatorySet = mandatorySkills.stream()
                .map(SkillSynonymUtil::normalizeSkill)
                .collect(Collectors.toSet());

        Set<String> optionalSet = optionalSkills.stream()
                .map(SkillSynonymUtil::normalizeSkill)
                .collect(Collectors.toSet());

        Set<String> matchedMandatory = new HashSet<>(resumeSet);
        matchedMandatory.retainAll(mandatorySet);

        Set<String> missingMandatory = new HashSet<>(mandatorySet);
        missingMandatory.removeAll(resumeSet);

        Set<String> matchedOptional = new HashSet<>(resumeSet);
        matchedOptional.retainAll(optionalSet);

        Set<String> missingOptional = new HashSet<>(optionalSet);
        missingOptional.removeAll(resumeSet);

        double mandatoryScore = mandatorySet.isEmpty() ? 0 :
                (matchedMandatory.size() * 100.0) / mandatorySet.size();

        double optionalScore = optionalSet.isEmpty() ? 0 :
                (matchedOptional.size() * 100.0) / optionalSet.size();

        double finalScore =
                (mandatoryScore * MANDATORY_WEIGHT) +
                        (optionalScore * OPTIONAL_WEIGHT);

        return new WeightedSkillMatchResult(
                matchedMandatory,
                missingMandatory,
                matchedOptional,
                missingOptional,
                Math.round(finalScore)
        );
    }
}