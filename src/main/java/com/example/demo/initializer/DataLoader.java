package com.example.demo.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName(Role.ERole.ROLE_ETUDIANT).isEmpty()) {
            roleRepository.save(new Role(Role.ERole.ROLE_ETUDIANT));
        }
        if (roleRepository.findByName(Role.ERole.ROLE_PROFESSEUR).isEmpty()) {
            roleRepository.save(new Role(Role.ERole.ROLE_PROFESSEUR));
        }
        if (roleRepository.findByName(Role.ERole.ROLE_ADMIN).isEmpty()) {
            roleRepository.save(new Role(Role.ERole.ROLE_ADMIN));
        }
    }
}