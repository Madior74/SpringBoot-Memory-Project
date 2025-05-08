package com.example.demo.initializer;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.admin.AdminService;
import com.example.demo.admin.RegisterAdminDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final AdminService adminService;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.seeder.admin-email}")
    private String adminEmail;

    @Value("${app.seeder.admin-password}")
    private String adminPassword;

    @Override
    public void run(String... args) throws Exception {
        // Vérifier si un admin existe déjà
        if (adminService.getAllAdmins().isEmpty()) {
            RegisterAdminDTO dto = new RegisterAdminDTO();
            dto.setNom("Super");
            dto.setPrenom("Admin");
            dto.setAdresse("123 Rue Admin");
            dto.setPaysDeNaissance("Sénégal");
            dto.setDateDeNaissance(LocalDate.of(1990, 1, 1));
            dto.setCni("ADMIN_CNI_001");
            dto.setIne("ADMIN_INE_001");
            dto.setTelephone("+221771234567");
            dto.setSexe("Homme");
            dto.setEmail(adminEmail); // "super@admin.com"
            dto.setPassword(passwordEncoder.encode(adminPassword)); // "password"
            dto.setRegionId(1L);
            dto.setDepartementId(1L);

            adminService.registerAdmin(dto);
            System.out.println(" Admin par défaut créé !");
        }
    }
}