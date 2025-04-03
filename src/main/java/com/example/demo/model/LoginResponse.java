package com.example.demo.model;

import lombok.Data;
public class LoginResponse {
    private String message;
    private String nom;
    private String role;

    public LoginResponse(String message, String nom, String role) {
        this.message = message;
        this.nom = nom;
        this.role = role;
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public String getNom() {
        return nom;
    }

    public String getRole() {
        return role;
    }
}