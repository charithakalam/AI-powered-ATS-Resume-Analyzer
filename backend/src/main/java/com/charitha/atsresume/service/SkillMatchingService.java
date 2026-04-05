package com.charitha.atsresume.service;

import com.charitha.atsresume.dto.SkillMatchResult;
import org.springframework.stereotype.Service;
import com.charitha.atsresume.util.SkillSynonymUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SkillMatchingService {

    public SkillMatchResult matchSkills(List<String> resumeSkills,
                                        List<String> jdSkills) {

        Set<String> resumeSet = resumeSkills.stream()
                .map(SkillSynonymUtil::normalizeSkill)
                .collect(Collectors.toSet());

        Set<String> jdSet = jdSkills.stream()
                .map(SkillSynonymUtil::normalizeSkill)
                .collect(Collectors.toSet());

        Set<String> matchedSkills = new HashSet<>(resumeSet);
        matchedSkills.retainAll(jdSet);

        Set<String> missingSkills = new HashSet<>(jdSet);
        missingSkills.removeAll(resumeSet);

        double matchPercentage = jdSet.isEmpty()
                ? 0
                : (matchedSkills.size() * 100.0) / jdSet.size();

        return new SkillMatchResult(
                matchedSkills,
                missingSkills,
                Math.round(matchPercentage)
        );
    }
}
