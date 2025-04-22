package com.example.demo.model;



import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "etudiants")
@Getter
@Setter


public class Etudiant extends Utilisateur {



    @OneToOne(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private DossierAdmission dossierAdmission;

}