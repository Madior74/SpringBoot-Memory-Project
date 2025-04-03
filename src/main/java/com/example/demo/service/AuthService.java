package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.model.Admin;
import org.springframework.stereotype.Service;

import com.example.demo.model.Etudiant;
import com.example.demo.model.Professeur;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.EtudiantRepository;
import com.example.demo.repository.ProfesseurRepository;
@Service
public class AuthService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ProfesseurRepository professeurRepository;

    @Autowired
    private AdminRepository adminRepository;

    public Object authenticate(String email, String password) {
        // Vérifie si c'est un étudiant
        Optional<Etudiant> etudiantOptional = etudiantRepository.findByEmail(email);
        if (etudiantOptional.isPresent()) {
            Etudiant etudiant = etudiantOptional.get();
            if (!etudiant.getPassword().equals(password)) {
                throw new RuntimeException("Mot de passe incorrect");
            }
            return etudiant;
        }

        // Vérifie si c'est un professeur
        Optional<Professeur> professeurOptional = professeurRepository.findByEmail(email);
        if (professeurOptional.isPresent()) {
            Professeur professeur = professeurOptional.get();
            if (!professeur.getPassword().equals(password)) {
                throw new RuntimeException("Mot de passe incorrect");
            }
            return professeur;
        }

        // Vérifie si c'est un administrateur
        Optional<Admin> adminOptional = adminRepository.findByEmail(email); 
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            if (!admin.getPassword().equals(password)) {
                throw new RuntimeException("Mot de passe incorrect");
            }
            return admin;
        }

        // Si aucun utilisateur n'est trouvé
        throw new RuntimeException("Utilisateur non trouvé");
    }
}