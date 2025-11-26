package com.alsolakyle.lab7.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data // Generates getters, setters for all fields
@Builder // Generates a builder pattern [cite: 164]
@AllArgsConstructor // Generates constructor with all arguments [cite: 165]
@NoArgsConstructor // Generates default constructor [cite: 166]
public class User {
    private int UID;
    private String username;
    private String password;
    private boolean enabled;

    @RestController // Indicates this class handles REST requests [cite: 177]
    @RequestMapping("/api/v1/users") // Sets the base URL endpoint [cite: 182]
    public static class UserController {

        @GetMapping // Maps HTTP GET requests to this method [cite: 185]
        public User getUser() {
            // Build and return a static user for testing
            return builder()
                    .username("blueJava")
                    .password("thisIsAPassword!")
                    .enabled(false)
                    .UID(0)
                    .build();
        }
    }
}