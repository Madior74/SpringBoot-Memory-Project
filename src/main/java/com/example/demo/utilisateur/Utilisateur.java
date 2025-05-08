package com.example.demo.utilisateur;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.enums.Role;
import com.example.demo.region.Region;
import com.example.demo.region.departement.Departement;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

import lombok.Data;


@MappedSuperclass
@Data
public abstract class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String adresse;
    private String paysDeNaissance;

     @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDeNaissance;



    private String imagePath;

    @Column(unique = true, nullable = false)
    private String cni;

    @Column(unique = true, nullable = false)
    private String ine;

    private String telephone;
    private String sexe;
    private String email;
 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @ManyToOne
    @JsonIgnoreProperties
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @ManyToOne
    @JsonIgnoreProperties
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateAjout;
}