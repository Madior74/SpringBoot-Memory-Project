package com.example.demo.etudiant.document;

import java.time.LocalDateTime;

import com.example.demo.etudiant.Etudiant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom; 
    private String type;
    
    private String cheminFichier; 

    private LocalDateTime dateDepot;


    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    public Document() {
    }

    public Document(String nom, String type, String cheminFichier, LocalDateTime dateDepot, Etudiant etudiant) {
        this.nom = nom;
        this.type = type;
        this.cheminFichier = cheminFichier;
        this.dateDepot = dateDepot;
        this.etudiant = etudiant;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCheminFichier() {
        return cheminFichier;
    }

    public void setCheminFichier(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }

    public LocalDateTime getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(LocalDateTime dateDepot) {
        this.dateDepot = dateDepot;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
