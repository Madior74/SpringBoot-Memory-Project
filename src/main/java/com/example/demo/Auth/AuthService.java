package com.example.demo.Auth;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.admin.Admin;
import com.example.demo.admin.AdminRepository;
import com.example.demo.etudiant.prinscription.Etudiant;
import com.example.demo.etudiant.prinscription.EtudiantRepository;
import com.example.demo.professeur.Professeur;
import com.example.demo.professeur.ProfesseurRepository;

@Service
public class AuthService {

   
    
 @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ProfesseurRepository professeurRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;


    public Object authenticate(String email, String password) {
        Optional<Etudiant> etudiantOptional = etudiantRepository.findByEmail(email);
        if (etudiantOptional.isPresent()) {
            Etudiant etudiant = etudiantOptional.get();
            if (!passwordEncoder.matches(password, etudiant.getPassword())) { // ⬅️ Vérification sécurisée
                throw new RuntimeException("Mot de passe incorrect");
            }
            return etudiant;
        }

        Optional<Professeur> professeurOptional = professeurRepository.findByEmail(email);
        if (professeurOptional.isPresent()) {
            Professeur professeur = professeurOptional.get();
            if (!passwordEncoder.matches(password, professeur.getPassword())) {
                throw new RuntimeException("Mot de passe incorrect");
            }
            return professeur;
        }

        Optional<Admin> adminOptional = adminRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            if (!passwordEncoder.matches(password, admin.getPassword())) {
                throw new RuntimeException("Mot de passe incorrect");
            }
            return admin;
        }

        throw new RuntimeException("Utilisateur non trouvé");
    }

//     public Utilisateur authenticate(String email, String password) {
//     Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
//         .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

//     if (!utilisateur.getPassword().equals(password)) {
//         throw new RuntimeException("Mot de passe incorrect");
//     }

//     return utilisateur;
// }

}