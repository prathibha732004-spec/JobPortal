package com.jobportal.controller;

import com.jobportal.model.Job;
import com.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//import jakarta.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private com.jobportal.repository.JobApplicationRepository applicationRepository;

    @Autowired
    private com.jobportal.service.EmailService emailService;

    @Autowired
    private com.jobportal.service.FileStorageService fileStorageService;

    @GetMapping
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @PostMapping(value = "/apply", consumes = { "multipart/form-data" })
    public ResponseEntity<?> applyForJob(
            @RequestParam("jobId") String jobId,
            @RequestParam("email") String email,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("phone") String phone,
            @RequestParam(required = false) String portfolio,
            @RequestParam("coverLetter") String coverLetter,
            @RequestParam("resumeFile") MultipartFile resumeFile) {

        String name = firstName + " " + lastName;

        return jobRepository.findById(Long.parseLong(jobId))
                .map(job -> {
                    // Store the file
                    String fileName = fileStorageService.storeFile(resumeFile);
                    String fileUrl = "/uploads/" + fileName;

                    // Persist to database
                    com.jobportal.model.JobApplication application = new com.jobportal.model.JobApplication();
                    application.setJobId(job.getId());
                    application.setJobTitle(job.getTitle());
                    application.setFirstName(firstName);
                    application.setLastName(lastName);
                    application.setEmail(email);
                    application.setPhone(phone);
                    application.setPortfolio(portfolio);
                    application.setResumeUrl(fileUrl);
                    application.setCoverLetter(coverLetter);
                    applicationRepository.save(application);

                    // Send email
                    emailService.sendApplicationConfirmation(email, job.getTitle(), name);

                    java.util.Map<String, String> response = new java.util.HashMap<>();
                    response.put("message", "Application submitted successfully");
                    response.put("resumeUrl", fileUrl);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Job> searchJobs(@RequestParam(required = false) String keyword,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String location) {
        System.out.println("DEBUG: Searching jobs - Keyword: '" + keyword + "', Category: '" + category
                + "', Location: '" + location + "'");
        List<Job> results = jobRepository.searchJobs(keyword, category, location);
        System.out.println("DEBUG: Search returned " + results.size() + " jobs.");
        return results;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        return jobRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobRepository.save(job);
    }
}
