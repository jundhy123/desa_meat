package com.desameat.website.config;

import com.desameat.website.model.ProfilDesa;
import com.desameat.website.model.Role;
import com.desameat.website.model.User;
import com.desameat.website.model.Wisata;
import com.desameat.website.repository.ProfilRepository;
import com.desameat.website.repository.RoleRepository;
import com.desameat.website.repository.UserRepository;
import com.desameat.website.repository.WisataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WisataRepository wisataRepository;

    @Autowired
    private ProfilRepository profilRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // =========================
        // 1. ROLE SEEDER
        // =========================
        Role adminRole = roleRepository.findByName("ADMIN").orElse(null);

        if (adminRole == null) {
            adminRole = new Role("ADMIN");
            roleRepository.save(adminRole);
            System.out.println("Role ADMIN created!");
        }

        Role userRole = roleRepository.findByName("USER").orElse(null);

        if (userRole == null) {
            userRole = new Role("USER");
            roleRepository.save(userRole);
            System.out.println("Role USER created!");
        }

        // =========================
        // 2. DEFAULT ADMIN
        // =========================
        if (!userRepository.existsByUsername("admin")) {

            User admin = new User();

            admin.setUsername("admin");
            admin.setEmail("admin@desameat.go.id");
            admin.setFullName("Administrator Desa Meat");

            // username: admin
            // password: admin123
            admin.setPassword(passwordEncoder.encode("admin123"));

            admin.setRole(adminRole);

            userRepository.save(admin);

            System.out.println("Default admin created!");
            System.out.println("Username : admin");
            System.out.println("Password : admin123");
        }

        // =========================
        // 3. PROFIL DESA
        // =========================
        User adminUser = userRepository.findByUsername("admin").orElse(null);

        if (adminUser != null && profilRepository.count() == 0) {

            ProfilDesa profil = new ProfilDesa();

            profil.setUser(adminUser);

            // Jika ada field lain di ProfilDesa
            // isi di sini

            profilRepository.save(profil);

            System.out.println("Profil Desa created!");
        }

        // =========================
        // 4. DATA WISATA
        // =========================
        if (wisataRepository.count() == 0) {

            Wisata pantaiMeat = new Wisata(
                    "Pantai Meat",
                    "Pantai dengan hamparan rumput hijau yang luas dan pasir putih bersih langsung di tepian Danau Toba.",
                    "Pesisir Danau, Desa Meat",
                    4.9,
                    "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=800&q=80",
                    "Camping Ground, Sewa Perahu, Spot Foto"
            );

            Wisata bukitMeat = new Wisata(
                    "Bukit Indah Meat",
                    "Perbukitan hijau dengan pemandangan Danau Toba yang sangat indah.",
                    "Bukit Tampahan, Desa Meat",
                    4.8,
                    "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?auto=format&fit=crop&w=800&q=80",
                    "Spot Foto, Trekking, Gazebo"
            );

            Wisata sawahMeat = new Wisata(
                    "Sawah Terasering Tampahan",
                    "Sawah terasering tradisional dengan suasana pedesaan yang asri.",
                    "Sawah Barat, Desa Meat",
                    4.7,
                    "https://images.unsplash.com/photo-1500937386664-56d1dfef3854?auto=format&fit=crop&w=800&q=80",
                    "Agrowisata, Jalur Sepeda, Gazebo"
            );

            wisataRepository.save(pantaiMeat);
            wisataRepository.save(bukitMeat);
            wisataRepository.save(sawahMeat);

            System.out.println("Wisata seeded successfully!");
        }

        System.out.println("Database seeding completed!");
    }
}