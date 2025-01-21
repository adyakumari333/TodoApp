package com.in28minutes.springboot.TodoApp.controller;

import com.in28minutes.springboot.TodoApp.model.User;
import com.in28minutes.springboot.TodoApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@Autowired
    private final UserRepository userRepository;
	
	@Autowired
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // Show login page
    }

//    @PostMapping("/login")
//    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
//        // Find the user by username
//        User user = userRepository.findByUsername(username).orElse(null);
//
//        if (user == null) {
//            model.addAttribute("error", "Invalid username or password");
//            return "login"; // Return to login page with error message
//        }
//
//        // Check if the password matches
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            model.addAttribute("error", "Invalid username or password");
//            return "login"; // Return to login page with error message
//        }
//
//        // Successful login, redirect to todos page or any page of your choice
//        return "redirect:/todos";
//    }
}
