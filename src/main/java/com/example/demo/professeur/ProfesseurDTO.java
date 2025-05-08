package com.example.demo.professeur;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.Specialite.Specialite;
import com.example.demo.enums.Role;
import com.example.demo.region.Region;
import com.example.demo.region.departement.Departement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesseurDTO {

    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String paysDeNaissance;
    private LocalDate dateDeNaissance;
    private String imagePath;
    private String cni;
    private String ine;
    private String telephone;
    private String sexe;
    private String email;
    private Role role;
    private String grade;
    private boolean estPermanent;
    private List<Specialite> specialites;
    private Region region;
    private Departement departement;
    private LocalDateTime dateAjout;

    // Constructeur depuis l'entité
    public ProfesseurDTO(Professeur professeur) {
        this.id = professeur.getId();
        this.nom = professeur.getNom();
        this.prenom = professeur.getPrenom();
        this.adresse = professeur.getAdresse();
        this.paysDeNaissance = professeur.getPaysDeNaissance();
        this.dateDeNaissance = professeur.getDateDeNaissance();
        this.imagePath = professeur.getImagePath();
        this.cni = professeur.getCni();
        this.ine = professeur.getIne();
        this.telephone = professeur.getTelephone();
        this.sexe = professeur.getSexe();
        this.email = professeur.getEmail();
        this.role = professeur.getRole();
        this.grade = professeur.getGrade();
        this.estPermanent = professeur.isEstPermanent();
        this.specialites = professeur.getSpecialites();
        this.region = professeur.getRegion();
        this.departement = professeur.getDepartement();
        this.dateAjout = professeur.getDateAjout();
    }

}
