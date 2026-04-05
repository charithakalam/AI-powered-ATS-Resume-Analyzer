package com.charitha.atsresume.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SkillSynonymUtil {

    private static final Map<String, String> SKILL_SYNONYMS = new HashMap<>();

    static {
        // Java ecosystem
        SKILL_SYNONYMS.put("spring", "spring boot");
        SKILL_SYNONYMS.put("springboot", "spring boot");
        SKILL_SYNONYMS.put("spring framework", "spring boot");

        // JavaScript ecosystem
        SKILL_SYNONYMS.put("js", "javascript");

        SKILL_SYNONYMS.put("angularjs", "angular");

        // DevOps / Cloud
        SKILL_SYNONYMS.put("aws cloud", "aws");
        SKILL_SYNONYMS.put("amazon web services", "aws");

        SKILL_SYNONYMS.put("react.js", "react");
        SKILL_SYNONYMS.put("react js", "react");
        SKILL_SYNONYMS.put("reactjs", "react");

        SKILL_SYNONYMS.put("node.js", "nodejs");
        SKILL_SYNONYMS.put("node js", "nodejs");
        SKILL_SYNONYMS.put("nodejs", "nodejs");
        SKILL_SYNONYMS.put("node", "nodejs");

        SKILL_SYNONYMS.put("html5", "html");
        SKILL_SYNONYMS.put("css3", "css");

        // Semantic mappings
        SKILL_SYNONYMS.put("frontend framework", "react");
        SKILL_SYNONYMS.put("backend framework", "spring boot");
        SKILL_SYNONYMS.put("cloud platform", "aws");
        SKILL_SYNONYMS.put("containerization", "docker");
    }

    public static String normalizeSkill(String skill) {
        String normalized = skill.toLowerCase(Locale.ROOT).trim();
        return SKILL_SYNONYMS.getOrDefault(normalized, normalized);
    }
}
