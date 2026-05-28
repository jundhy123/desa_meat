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
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        if (error != null) {
            model.addAttribute("error", "Username atau Password yang Anda masukkan salah.");
        }
        return "auth/login";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "auth/register";
    }

    @PostMapping("/register-process")
    public String processRegister(@ModelAttribute("registerRequest") RegisterRequest registerRequest, Model model) {
        try {
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(registerRequest.getPassword());
            user.setFullName(registerRequest.getFullName());
            authService.registerUser(user);
            return "redirect:/auth/login?registered=true";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "auth/register";
        }
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
