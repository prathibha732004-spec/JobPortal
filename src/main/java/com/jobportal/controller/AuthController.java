package com.jobportal.controller;

import com.jobportal.model.User;
import com.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.jobportal.service.EmailService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Random;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Email is already in use!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        userRepository.save(user);

        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("userId", String.valueOf(user.getId()));
        response.put("fullName", user.getFullName());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Validate password securely here in production. Doing plane-text comparison
            // for beginner-mode.
            if (user.getPassword().equals(loginRequest.getPassword())) {
                // Generate 6-digit OTP
                String otp = String.format("%06d", new Random().nextInt(999999));
                otpStorage.put(user.getEmail(), otp);

                // Send OTP
                emailService.sendOtpVerification(user.getEmail(), otp);

                Map<String, Object> response = new HashMap<>();
                response.put("message", "OTP sent to your email.");
                response.put("requireOtp", true);
                response.put("email", user.getEmail());
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
            }
        }

        Map<String, String> response = new HashMap<>();
        response.put("error", "Invalid email or password!");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> otpRequest) {
        String email = otpRequest.get("email");
        String otp = otpRequest.get("otp");

        if (email != null && otp != null && otp.equals(otpStorage.get(email))) {
            otpStorage.remove(email); // valid OTP, remove it

            Optional<User> optionalUser = userRepository.findByEmail(email);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                Map<String, String> response = new HashMap<>();
                response.put("message", "Login successful");
                response.put("userId", String.valueOf(user.getId()));
                response.put("fullName", user.getFullName());
                response.put("email", user.getEmail());
                return ResponseEntity.ok(response);
            }
        }

        Map<String, String> response = new HashMap<>();
        response.put("error", "Invalid or expired OTP!");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
