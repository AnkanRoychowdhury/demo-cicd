package tech.ankanroychowdhury.app.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/v1/users")
public class UserController{

    @GetMapping
    public ResponseEntity<String> getUsers() {
        try {
            return ResponseEntity.status(200).body("Hello User: " + generateRandomEmail(10));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Error");
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.status(200).body("User OK");
    }

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String[] DOMAINS = {"gmail.com", "yahoo.com", "outlook.com", "example.com"};

    public String generateRandomEmail(int length) {
        Random random = new Random();
        StringBuilder email = new StringBuilder();

        for (int i = 0; i < length; i++) {
            email.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        email.append("@").append(DOMAINS[random.nextInt(DOMAINS.length)]);
        return email.toString();
    }
}
