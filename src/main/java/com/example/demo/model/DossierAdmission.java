package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data

public class DossierAdmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean copieCni;
    private boolean releveNotes;
    private boolean diplome;
    private boolean fraisPayes;
    private String remarque;

    private String statut; // EN_ATTENTE, COMPLET, REFUSE

    @OneToOne()
    @JsonBackReference
    private Etudiant etudiant;

    

}