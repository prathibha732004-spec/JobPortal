package com.jobportal.controller;

import com.jobportal.model.ContactMessage;
import com.jobportal.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private com.jobportal.repository.ContactMessageRepository contactRepository;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<?> sendContactMessage(@RequestBody ContactMessage contactMessage) {
        try {
            contactRepository.save(contactMessage);
            emailService.sendContactMessage(contactMessage);
            return ResponseEntity.ok().body("{\"success\": true, \"message\": \"Message sent successfully!\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("{\"success\": false, \"message\": \"Failed to send message: " + e.getMessage() + "\"}");
        }
    }
}
