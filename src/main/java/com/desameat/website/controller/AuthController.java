package com.desameat.website.controller;

import com.desameat.website.dto.LoginRequest;
import com.desameat.website.dto.RegisterRequest;
import com.desameat.website.model.User;
import com.desameat.website.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "auth/login";
    }

    @PostMapping("/login-process")
    public String processLogin(@ModelAttribute("loginRequest") LoginRequest loginRequest, Model model) {
        boolean valid = authService.checkCredentials(loginRequest.getUsername(), loginRequest.getPassword());
        if (valid) {
            return "redirect:/admin/dashboard";
        }
        model.addAttribute("error", "Username atau Password yang Anda masukkan tidak terdaftar.");
        return "auth/login";
    }

    @PostMapping("/api/register")
    @ResponseBody
    public ResponseEntity<?> registerApiUser(@RequestBody RegisterRequest req) {
        try {
            User u = new User();
            u.setUsername(req.getUsername());
            u.setEmail(req.getEmail());
            u.setPassword(req.getPassword());
            u.setFullName(req.getFullName());
            User registered = authService.registerUser(u);
            return ResponseEntity.ok(registered);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
