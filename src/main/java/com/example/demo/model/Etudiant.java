package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@JsonIgnoreProperties(ignoreUnknown = true)

    @Entity
    @Table(name = "etudiants")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class Etudiant {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String prenom;
        private String nom;
        private String adresse;

        @Column(name = "pays_de_naissance")
        private String paysDeNaissance;

        @JsonDeserialize(using = LocalDateDeserializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate dateDeNaissance;

        @Column(name = "image_path")
        private String imagePath;

        @Column(unique = true, nullable = false)
        private String cni;

        @Column(unique = true, nullable = false)
        private String ine;


        @ManyToOne
        @JoinColumn(name = "region_id",nullable = false)
        private Region region;

        @Column(nullable = false)
        private String role; // Exemple : "ROLE_ETUDIANT"
        
        @ManyToOne
        @JoinColumn(name = "departement_id",nullable = false)
        private Departement departement;



        @ManyToOne
        @JoinColumn(name = "filiere_id", nullable = false)
        private Filiere filiere;

        @ManyToOne
        @JoinColumn(name = "niveau_id", nullable = false)
        private Niveau niveau;

        @ManyToOne
        @JoinColumn(name = "session_id", nullable = false)
        private Session session;

        private String telephone;
        private String sexe;
        private String email;
        private String password;

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        private LocalDateTime dateAjout = LocalDateTime.now();
    }
