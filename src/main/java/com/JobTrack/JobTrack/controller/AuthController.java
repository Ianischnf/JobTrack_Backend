package com.JobTrack.JobTrack.controller;


import com.JobTrack.JobTrack.DTO.RegisterRequestDTO;
import com.JobTrack.JobTrack.configuration.JwtUtils;
import com.JobTrack.JobTrack.entity.User;
import com.JobTrack.JobTrack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {

        System.out.println("register appeler");


        if (!request.password().equals(request.confirmPassword())) {
            return ResponseEntity.badRequest().body("Les mots de passe ne correspondent pas");
        }

        if (userRepository.findByEmail(request.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Email déjà utilisé");
        }

        User user = new User();

        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));

        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()
                    )
            );

            if (authentication.isAuthenticated()) {
                User authenticatedUser = userRepository.findByEmail(user.getEmail())
                        .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

                Map<String, Object> authData = new HashMap<>();

                authData.put(
                        "token",
                        jwtUtils.generateToken(authenticatedUser.getEmail())
                );

                authData.put("firstName", authenticatedUser.getFirstName());
                authData.put("lastName", authenticatedUser.getLastName());
                authData.put("email", authenticatedUser.getEmail());
                authData.put("type", "Bearer");

                return ResponseEntity.ok(authData);
            }

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");

        } catch (AuthenticationException e) {
            log.error(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }
}
