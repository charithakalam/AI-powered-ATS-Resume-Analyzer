package com.charitha.atsresume.service;

import com.charitha.atsresume.dto.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AtsExplainabilityService {

    private List<String> generateResumeFeedback(String resumeText) {
        List<String> feedback = new ArrayList<>();

        String lower = resumeText.toLowerCase();

        // ❌ No numbers → weak impact
        if (!lower.matches(".*\\d+.*")) {
            feedback.add("Your resume lacks measurable achievements. Add metrics like percentages or results.");
        }

        // ❌ No projects section
        if (!lower.contains("project")) {
            feedback.add("Projects section is missing or weak. Add 2-3 strong projects.");
        }

        // ❌ Generic words
        if (lower.contains("hardworking") || lower.contains("team player")) {
            feedback.add("Avoid generic terms like 'hardworking'. Use specific achievements instead.");
        }

        return feedback;
    }

    public AtsExplanationResult explain(
            WeightedSkillMatchResult skillResult,
            ExperienceMatchResult experienceResult,
            FinalAtsResult finalResult,
            String resumeText) {

        Set<String> reasons = new LinkedHashSet<>();

        // ✅ Resume feedback
        reasons.addAll(generateResumeFeedback(resumeText));

        // ✅ Combine all missing skills (MANDATORY + OPTIONAL)
        Set<String> allMissingSkills = new HashSet<>();
        allMissingSkills.addAll(skillResult.getMissingMandatorySkills());
        allMissingSkills.addAll(skillResult.getMissingOptionalSkills());

        // ✅ Skill suggestions (single clean block)
        for (String skill : allMissingSkills) {

            String s = skill.trim().toLowerCase();

            if (s.contains("spring boot")) {
                reasons.add("Spring Boot is a key requirement. Try adding backend projects or REST APIs built using Spring Boot.");
            }
            else if (s.contains("aws")) {
                reasons.add("Your resume lacks AWS experience. Consider adding projects using EC2, S3, or cloud deployments.");
            }
            else if (s.contains("docker")) {
                reasons.add("Docker is missing. Add containerization experience or deployment workflows using Docker.");
            }
            else {
                reasons.add("You are missing " + skill + ", which is important for this role.");
            }
        }

        // ✅ Experience (ONLY ONCE GUARANTEED)
        double expPercent = experienceResult.getExperienceMatchPercentage();

        String expMessage;
        if (expPercent == 0) {
            expMessage = "You do not meet the required experience level";
        }
        else if (expPercent < 100) {
            expMessage = "Improve your experience level to boost your score";
        }
        else {
            expMessage = "Experience requirement is fully satisfied";
        }

        reasons.add(expMessage);
        System.out.println("FINAL REASONS: " + reasons);
        // ✅ FINAL SAFE DEDUPLICATION (extra protection)
        List<String> finalList = new ArrayList<>(new LinkedHashSet<>(reasons));

        return new AtsExplanationResult(
                finalResult.getFinalAtsScore(),
                finalList
        );
    }
}
