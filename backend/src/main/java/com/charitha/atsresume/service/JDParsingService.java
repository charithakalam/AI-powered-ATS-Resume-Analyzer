package com.charitha.atsresume.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JDParsingService {

    public Map<String, Object> parseJD(String jdText) {

        jdText = jdText.toLowerCase();

        List<String> mandatorySkills = new ArrayList<>();
        List<String> optionalSkills = new ArrayList<>();
        int experience = 0;

        // 🔥 Extract mandatory skills
        if (jdText.contains("key skills")) {
            String section = jdText.split("key skills")[1];

            if (section.contains("good to have")) {
                section = section.split("good to have")[0];
            }

            mandatorySkills = extractSkillsFromSection(section, false);
        }

        // 🔥 Extract optional skills
        if (jdText.contains("good to have")) {
            String section = jdText.split("good to have")[1];

            if (section.contains("experience")) {
                section = section.split("experience")[0];
            }

            optionalSkills = extractSkillsFromSection(section, true);
        }

        // 🔥 Extract experience
        experience = extractExperience(jdText);

        Map<String, Object> result = new HashMap<>();
        result.put("mandatorySkills", mandatorySkills);
        result.put("optionalSkills", optionalSkills);
        result.put("experience", experience);

        return result;
    }

    private List<String> extractSkillsFromSection(String section, boolean isOptional){
        List<String> skills = new ArrayList<>();

        String[] lines = section.split("\n");

        for (String line : lines) {
            line = line.replace("-", "").trim();

            if (line.isEmpty() || line.contains(":") || line.matches(".*\\d+.*")) {
                continue;
            }

            // 🔥 STEP 7: Semantic Mapping
            String lower = line.toLowerCase();

            if (lower.contains("frontend framework")) {
                skills.add("react");
            }
            else if (lower.contains("backend framework")) {
                skills.add("spring boot");
            }
            else if (lower.contains("cloud platform") && isOptional) {
                skills.add("aws");
            }
            else if (lower.contains("containerization") && isOptional) {
                skills.add("docker");
            }
            else {
                skills.add(line);
            }
        }

        return skills;
    }

    private int extractExperience(String text) {
        if (text.contains("year")) {
            String[] words = text.split(" ");

            for (int i = 0; i < words.length; i++) {
                if (words[i].matches("\\d+")) {
                    return Integer.parseInt(words[i]);
                }
            }
        }
        return 0;
    }
}