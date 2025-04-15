package com.example.demo.model;



import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "etudiants")
@Getter
@Setter
public class Etudiant extends Utilisateur {

    @ManyToOne
    @JoinColumn(name = "filiere_id", nullable = false)
    private Filiere filiere;

    @ManyToOne
    @JoinColumn(name = "niveau_id", nullable = false)
    private Niveau niveau;

    @ManyToOne
    @JoinColumn(name = "anneeAcademique_id", nullable = false)
    private AnneeAcademique anneeAcademique;
}