package com.in28minutes.springboot.TodoApp.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.in28minutes.springboot.TodoApp.model.User;
import com.in28minutes.springboot.TodoApp.repository.UserRepository;

@Controller
public class SignupController {

    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String signup() {
        logger.info("GET /signup accessed");
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@RequestParam String username, @RequestParam String password) {
        logger.info("Processing signup for username: {}", username);
        
        if (userRepository.findByUsername(username).isPresent()) {
            logger.warn("Signup failed - username already exists: {}", username);
            return "redirect:/signup?error";
        }

        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            userRepository.save(user);
            logger.info("User successfully created: {}", username);
            return "redirect:/login?success";
        } catch (Exception e) {
            logger.error("Error during signup: ", e);
            return "redirect:/signup?error";
        }
    }
}