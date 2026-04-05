package com.charitha.atsresume.controller;

import java.util.List;
import com.charitha.atsresume.dto.ResumeAnalysisRequest;
import com.charitha.atsresume.dto.SkillMatchResult;
import com.charitha.atsresume.dto.WeightedResumeAnalysisRequest;
import com.charitha.atsresume.dto.WeightedSkillMatchResult;
import com.charitha.atsresume.service.*;
import com.charitha.atsresume.dto.ExperienceAnalysisRequest;
import com.charitha.atsresume.dto.ExperienceMatchResult;
import com.charitha.atsresume.dto.FinalAtsRequest;
import com.charitha.atsresume.dto.FinalAtsResult;
import com.charitha.atsresume.dto.FullAtsAnalysisRequest;
import com.charitha.atsresume.dto.FullAtsAnalysisResult;
import com.charitha.atsresume.model.AtsResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Sort;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private AtsMatchingService atsMatchingService;

    @PostMapping("/analyze")
    public SkillMatchResult analyzeResume(
            @RequestBody ResumeAnalysisRequest request) {

        return atsMatchingService.analyze(
                request.getResumeSkills(),
                request.getJdSkills()
        );
    }

    @Autowired
    private WeightedSkillMatchingService weightedSkillMatchingService;

    @PostMapping("/analyze/weighted")
    public WeightedSkillMatchResult analyzeWeighted(
            @RequestBody WeightedResumeAnalysisRequest request) {

        return weightedSkillMatchingService.matchSkills(
                request.getResumeSkills(),
                request.getMandatorySkills(),
                request.getOptionalSkills()
        );
    }

    @Autowired
    private ExperienceMatchingService experienceMatchingService;

    @PostMapping("/analyze/experience")
    public ExperienceMatchResult analyzeExperience(
            @RequestBody ExperienceAnalysisRequest request) {

        return experienceMatchingService.matchExperience(
                request.getResumeExperience(),
                request.getJdExperience()
        );
    }

    @Autowired
    private FinalAtsScoringService finalAtsScoringService;

    @PostMapping("/analyze/final")
    public FinalAtsResult analyzeFinalAts(
            @RequestBody FinalAtsRequest request) {

        return finalAtsScoringService.calculateFinalScore(
                request.getSkillScore(),
                request.getExperienceScore()
        );
    }

    @Autowired
    private FullAtsAnalysisService fullAtsAnalysisService;

    @PostMapping("/analyze/full")
    public FullAtsAnalysisResult analyzeFullAts(
            @RequestBody FullAtsAnalysisRequest request,
            @RequestParam Long userId) {
        request.setResumeId("Manual Entry");
        request.setUserId(userId); // ✅ IMPORTANT

        return fullAtsAnalysisService.analyze(request);
    }

    @Autowired
    private AtsHistoryService atsHistoryService;

    @GetMapping("/history")
    public Page<AtsResultEntity> getAtsHistory(
            @RequestParam Long userId,
            @PageableDefault(
                    size = 5,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable) {

        return atsHistoryService.getHistoryByUserId(userId, pageable);
    }

    @Autowired
    private SkillExtractionService skillService;

    @Autowired
    private JDParsingService jdParsingService;

    private String extractTextFromPDF(MultipartFile file) throws IOException {
        PDDocument document = PDDocument.load(file.getInputStream());
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadResume(@RequestParam("file") MultipartFile file) {
        try {

            if (file == null || file.isEmpty()) {
                return ResponseEntity.badRequest().body("File is empty");
            }

            String content = extractTextFromPDF(file);

            List<String> skills = skillService.extractSkills(content);

            Map<String, Object> response = new HashMap<>();
            response.put("text", content);
            response.put("skills", skills);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 THIS IS KEY
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing file: " + e.getMessage());
        }
    }

    @PostMapping("/analyze-upload")
    public ResponseEntity<?> analyzeUploadedResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jobDescription") String jobDescription,
            @RequestParam("userId") Long userId) {
        try {
            System.out.println("Received JD: " + jobDescription);

            String content = extractTextFromPDF(file);


            List<String> resumeSkills = skillService.extractSkills(content);
            List<String> jdSkills = skillService.extractSkills(jobDescription);

            FullAtsAnalysisRequest request = new FullAtsAnalysisRequest();
            request.setResumeText(content);

            int resumeExp = skillService.extractExperience(content);

            Map<String, Object> parsedJD = jdParsingService.parseJD(jobDescription);

            List<String> mandatorySkills = (List<String>) parsedJD.get("mandatorySkills");
            List<String> optionalSkills = (List<String>) parsedJD.get("optionalSkills");
            int jdExperience = (int) parsedJD.get("experience");

            request.setResumeSkills(resumeSkills);

            request.setMandatorySkills(mandatorySkills);
            request.setOptionalSkills(optionalSkills);

            request.setJdExperience(jdExperience);
            request.setResumeExperience(resumeExp);

            request.setResumeExperience(resumeExp);
            request.setJdExperience(jdExperience);

            request.setUserId(userId);

            String fileName = file.getOriginalFilename();
            request.setResumeId(fileName);

            FullAtsAnalysisResult result = fullAtsAnalysisService.analyze(request);

            return ResponseEntity.ok(result);

        } catch (Exception e) {
            e.printStackTrace(); // IMPORTANT
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing file");
        }
    }
}
