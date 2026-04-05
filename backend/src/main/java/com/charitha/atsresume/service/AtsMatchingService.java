package com.charitha.atsresume.service;

import com.charitha.atsresume.dto.SkillMatchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtsMatchingService {

    @Autowired
    private SkillMatchingService skillMatchingService;

    public SkillMatchResult analyze(List<String> resumeSkills,
                                    List<String> jdSkills) {

        return skillMatchingService.matchSkills(resumeSkills, jdSkills);
    }
}
