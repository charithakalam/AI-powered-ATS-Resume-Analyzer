package com.charitha.atsresume.service;

import com.charitha.atsresume.util.SkillSynonymUtil;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

@Service
public class SkillExtractionService {

    private static final List<String> SKILLS = Arrays.asList(
            "java", "python", "c++", "sql", "react", "node.js",
            "spring boot", "html", "css", "javascript",
            "flask", "aws", "docker", "git", "tableau"
    );

    public List<String> extractSkills(String text) {
        text = text.toLowerCase()
                .replace(".", "")
                .replace("-", " ");

        List<String> skills = Arrays.asList(
                "java", "python", "c++", "sql", "react", "node.js",
                "spring boot", "html", "css", "javascript",
                "flask", "aws", "docker", "git", "tableau"
        );

        Set<String> foundSkills = new HashSet<>();

        for (String skill : skills) {
            if (text.contains(skill)) {
                foundSkills.add(SkillSynonymUtil.normalizeSkill(skill));
            }
        }

        return new ArrayList<>(foundSkills);
    }
    public int extractExperience(String text) {
        text = text.toLowerCase();

        // simple regex to find numbers like "2 years", "3 yrs"
        Pattern pattern = Pattern.compile("(\\d+)\\s*(years|year|yrs|yr)");
        Matcher matcher = pattern.matcher(text);

        int maxExperience = 0;

        while (matcher.find()) {
            int exp = Integer.parseInt(matcher.group(1));
            maxExperience = Math.max(maxExperience, exp);
        }

        return maxExperience;
    }
}