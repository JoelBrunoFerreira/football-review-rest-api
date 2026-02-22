package com.footballreview.api.controllers;

import com.footballreview.api.dtos.LoginDto;
import com.footballreview.api.dtos.RegisterDto;
import com.footballreview.api.entities.AppUser;
import com.footballreview.api.entities.Role;
import com.footballreview.api.enums.RoleName;
import com.footballreview.api.repositories.AppUserRepository;
import com.footballreview.api.repositories.RoleRepository;
import com.footballreview.api.security.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private AppUserRepository appUserRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AppUserRepository appUserRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    // POST -> Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(authentication);
        return ResponseEntity.ok(token);
    }

    // POST -> Register new AppUser
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto) {

        if(appUserRepository.existsByUsername(registerDto.getUserName())) {
            return ResponseEntity.badRequest().body("Username is already in use");
        }

        AppUser appUser = new AppUser();
        appUser.setUsername(registerDto.getUserName());
        appUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Role roles = roleRepository.findByRole(RoleName.ROLE_USER).orElseThrow(()
                -> new RuntimeException("Role not found in database"));

        appUser.setRoles(Collections.singletonList(roles));
        appUserRepository.save(appUser);
        return ResponseEntity.ok("User registered successfully");
    }

    // POST -> Logout

}
