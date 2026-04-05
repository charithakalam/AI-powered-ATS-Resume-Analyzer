package com.charitha.atsresume.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jd")
public class JobDescriptionController {

    @GetMapping("/test")
    public String testJD() {
        return "JD controller is working";
    }

    @PostMapping("/analyze")
    public String analyzeJD(@RequestParam("jobDescription") String jobDescription) {
        System.out.println("JD RECEIVED: " + jobDescription);
        return jobDescription;
    }
}
