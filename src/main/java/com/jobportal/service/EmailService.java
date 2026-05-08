package com.jobportal.service;

import com.jobportal.model.ContactMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactMessage(ContactMessage contactMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("hirebridge333@gmail.com");
        message.setReplyTo(contactMessage.getEmail());
        message.setSubject("New Contact Form Message: " + contactMessage.getSubject());
        message.setText("Name: " + contactMessage.getName() +
                "\nEmail: " + contactMessage.getEmail() +
                "\n\nMessage:\n" + contactMessage.getMessage());

        mailSender.send(message);
    }

    public void sendOtpVerification(String toEmail, String otp) {
        new Thread(() -> {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(toEmail);
                message.setSubject("Your OTP Verification Code");
                message.setText("Dear User,\n\nYour OTP for login verification is: " + otp
                        + "\n\nDo not share this code with anyone.\n\nBest Regards,\nHireBridge Team");

                mailSender.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendApplicationConfirmation(String toEmail, String jobTitle, String applicantName) {
        new Thread(() -> {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(toEmail);
                message.setSubject("Application Submitted: " + jobTitle);
                message.setText("Dear " + applicantName + ",\n\n" +
                        "Thank you for applying for the position of " + jobTitle + " at HireBridge.\n" +
                        "We have successfully received your application. Our talent acquisition team will review your profile and get back to you if your skills match our requirements.\n\n"
                        +
                        "Best Regards,\n" +
                        "The HireBridge Team");

                mailSender.send(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
