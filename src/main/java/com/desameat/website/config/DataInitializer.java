package com.desameat.website.config;

import com.desameat.website.model.Role;
import com.desameat.website.model.User;
import com.desameat.website.repository.RoleRepository;
import com.desameat.website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Pastikan role ADMIN dan USER ada
        Role adminRole = roleRepository.findByName("ADMIN").orElseGet(() -> roleRepository.save(new Role("ADMIN")));
        roleRepository.findByName("USER").orElseGet(() -> roleRepository.save(new Role("USER")));

        // Buat akun admin default jika belum ada
        Optional<User> adminOpt = userRepository.findByUsername("admin");
        if (adminOpt.isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@desameat.local");
            admin.setFullName("Administrator");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(adminRole);
            userRepository.save(admin);
            System.out.println("[DataInitializer] Default admin created: username=admin password=admin123");
        }
    }
}
