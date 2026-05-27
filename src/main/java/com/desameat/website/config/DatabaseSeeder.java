package com.desameat.website.config;

import com.desameat.website.model.Role;
import com.desameat.website.model.User;
import com.desameat.website.model.Wisata;
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
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 1. Seed Roles
        Role adminRole = roleRepository.findByName("ADMIN").orElse(null);
        if (adminRole == null) {
            adminRole = new Role("ADMIN");
            roleRepository.save(adminRole);
        }

        Role userRole = roleRepository.findByName("USER").orElse(null);
        if (userRole == null) {
            userRole = new Role("USER");
            roleRepository.save(userRole);
        }

        // 2. Seed Default Admin User (username: admin, password: admin)
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@desameat.go.id");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setFullName("Administrator Desa Meat");
            admin.setRole(adminRole);
            userRepository.save(admin);
            System.out.println("Default admin user created successfully! (admin/admin)");
        }

        // 3. Seed Default Wisata Destinations if empty
        if (wisataRepository.count() == 0) {
            Wisata pantaiMeat = new Wisata(
                "Pantai Meat",
                "Pantai dengan hamparan rumput hijau yang luas dan pasir putih bersih langsung di tepian Danau Toba. Sangat populer untuk tempat berkemah (camping ground), bermain kano, serta menikmati senja.",
                "Pesisir Danau, Desa Meat",
                4.9,
                "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?auto=format&fit=crop&w=800&q=80",
                "Camping Ground, Sewa Perahu, Kamar Mandi, Spot Foto, Warung Makan"
            );

            Wisata bukitMeat = new Wisata(
                "Bukit Indah Meat",
                "Perbukitan hijau yang berdiri megah mengitari desa. Merupakan lokasi terbaik untuk menikmati matahari terbit (sunrise) dan keindahan lanskap Danau Toba yang membentang luas dari ketinggian.",
                "Bukit Tampahan, Desa Meat",
                4.8,
                "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?auto=format&fit=crop&w=800&q=80",
                "Spot Foto, Area Trekking, Gazebo, Tempat Parkir"
            );

            Wisata sawahMeat = new Wisata(
                "Sawah Terasering Tampahan",
                "Sawah terasering tradisional berundak-undak peninggalan leluhur yang subur. Memberikan nuansa hijau royo-royo pedesaan yang asri dan sejuk berlatarkan pegunungan Toba.",
                "Sawah Barat, Desa Meat",
                4.7,
                "https://images.unsplash.com/photo-1500937386664-56d1dfef3854?auto=format&fit=crop&w=800&q=80",
                "Agrowisata, Spot Foto, Jalur Sepeda, Gazebo"
            );

            wisataRepository.save(pantaiMeat);
            wisataRepository.save(bukitMeat);
            wisataRepository.save(sawahMeat);
            System.out.println("Default Wisata destinations seeded successfully!");
        }
    }
}
