package com.example.demo.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "professeur")
@Data // Génère les getters, setters, toString, hashCode et equals
@NoArgsConstructor // Génère un constructeur sans paramètres
@AllArgsConstructor // Génère un constructeur avec tous les paramètres
public class Professeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String adresse;
    

    private String paysDeNaissance;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateDeNaissance;


   
    private String imagePath;

    @Column(unique = true ,nullable = false)
    private String cni;

    @Column(unique = true ,nullable = false)
    private String ine;

    private String  telephone;
    private String sexe;
    private String email;
    private String password;

    @Column(nullable = false)
    private String role; 

    
    @ManyToOne
    @JoinColumn(name = "region_id",nullable = false)
    private Region region;
    
    @ManyToOne
    @JoinColumn(name = "departement_id",nullable = false)
    private Departement departement;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateAjout;

    @ManyToMany(cascade = CascadeType.MERGE )
    @JoinTable(
        name = "professeur_specialite",
        joinColumns = @JoinColumn(name = "professeur_id"),
        inverseJoinColumns = @JoinColumn(name = "specialite_id")
    )
    @JsonIgnoreProperties("professeurs") // Empêche la récursion infinie
    private List<Specialite> specialites ;

    
}