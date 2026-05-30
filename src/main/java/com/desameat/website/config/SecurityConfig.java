package com.desameat.website.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // =========================
            // CSRF (DEV ONLY)
            // =========================
            .csrf(csrf -> csrf.disable())

            // =========================
            // AUTHORIZE REQUEST
            // =========================
            .authorizeHttpRequests(auth -> auth

                // STATIC RESOURCES
                .requestMatchers("/css/**", "/js/**", "/images/**", "/uploads/**", "/favicon.ico", "/webjars/**").permitAll()
                .requestMatchers("/error").permitAll()

                // AUTHENTICATION PATHS
                .requestMatchers("/auth/**").permitAll()

                // ADMIN AREA & CRUD OPERATIONS (MUST BE BEFORE PUBLIC PAGES TO OVERRIDE)
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/*/admin/**").hasRole("ADMIN")
                .requestMatchers("/profil-desa/admin/**").hasRole("ADMIN")

                // PUBLIC PAGES (READ-ONLY / FORM SUBMISSION)
                .requestMatchers(
                        "/",
                        "/home",
                        "/profil-desa/**",
                        "/berita/**",
                        "/wisata/**",
                        "/umkm/**",
                        "/layanan/**",
                        "/organisasi/**",
                        "/admin/organisasi/**", // Allow public access to organization management as requested
                        "/kontak/**",
                        "/galeri/**"
                ).permitAll()

                // DEFAULT RULE
                .anyRequest().authenticated()
            )

            // =========================
            // LOGIN CONFIG
            // =========================
            .formLogin(form -> form
                    .loginPage("/auth/login")
                    .loginProcessingUrl("/auth/login-process")
                    .defaultSuccessUrl("/admin/dashboard", true)
                    .failureUrl("/auth/login?error=true")
                    .permitAll()
            )

            // =========================
            // LOGOUT CONFIG (FIXED)
            // =========================
            .logout(logout -> logout
                    .logoutUrl("/auth/logout")
                    .logoutSuccessUrl("/berita")
                    .invalidateHttpSession(true)      // 🔥 FIX SESSION
                    .clearAuthentication(true)        // 🔥 FIX AUTH CACHE
                    .deleteCookies("JSESSIONID")      // 🔥 FIX COOKIE STUCK
            );

        return http.build();
    }

    // =========================
    // PASSWORD ENCODER
    // =========================
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}