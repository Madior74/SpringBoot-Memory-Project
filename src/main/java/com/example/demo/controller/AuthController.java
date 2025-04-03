package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Admin;
import com.example.demo.model.Etudiant;
import com.example.demo.model.LoginRequest;
import com.example.demo.model.LoginResponse;
import com.example.demo.model.Professeur;
import com.example.demo.service.AuthService;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping(value = "/api/auth", produces = "application/json;charset=UTF-8")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Object user = authService.authenticate(request.getEmail(), request.getPassword());

            if (user instanceof Etudiant) {
                Etudiant etudiant = (Etudiant) user;
                return ResponseEntity.ok(new LoginResponse("Connexion réussie", etudiant.getNom(), "ROLE_ETUDIANT"));
            } else if (user instanceof Professeur) {
                Professeur professeur = (Professeur) user;
                return ResponseEntity.ok(new LoginResponse("Connexion réussie", professeur.getNom(), "ROLE_PROFESSEUR"));
            } else if (user instanceof Admin) {
                Admin admin = (Admin) user;
                return ResponseEntity.ok(new LoginResponse("Connexion réussie", admin.getNom(), "ROLE_ADMIN"));
            }

            throw new RuntimeException("Type d'utilisateur inconnu");
        } catch (RuntimeException e) {
            throw e; // Laisse GlobalExceptionHandler gérer l'exception
        }
    }
}



// public class AuthController {

//     @Autowired
//     private AuthService authService;

//     @PostMapping("/login")
//     public ResponseEntity<?> login(@RequestBody LoginRequest request) {
//         try {
//             Etudiant etudiant = authService.authenticate(request.getEmail(), request.getPassword());
//             return ResponseEntity.ok(new LoginResponse("Connexion réussie", etudiant.getNom()));
//         } catch (RuntimeException e) {
//             throw e; // Laissez GlobalExceptionHandler gérer l'exception
//         }
//     }
//     @PostMapping("/logout")
//     public ResponseEntity<?> logout(HttpSession session) {
//         // Invalide la session
//         session.invalidate();
//         return ResponseEntity.ok("Déconnexion réussie");
//     }
// }