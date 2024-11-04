package org.example.partonejwtauth.controllers;

import lombok.RequiredArgsConstructor;
import org.example.partonejwtauth.dto.AuthRequest;
import org.example.partonejwtauth.entity.User;
import org.example.partonejwtauth.repository.JpaUserRepository;
import org.example.partonejwtauth.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final JpaUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JpaUserRepository userRepository, AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService, JwtUtils jwtUtils, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthRequest authRequest) {
        Optional<User> userExist = userRepository.findByUsername(authRequest.getUsername());
        if (userExist.isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User user = new User();

        user.setUsername(authRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        final UserDetails user = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtils.generateToken(user);
        return ResponseEntity.ok(jwt);
    }
}
