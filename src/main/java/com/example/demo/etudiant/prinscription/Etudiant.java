package com.example.demo.etudiant.prinscription;



import java.util.ArrayList;
import java.util.List;

import com.example.demo.enums.Role;
import com.example.demo.etudiant.admission.DossierAdmission;
import com.example.demo.etudiant.document.Document;
import com.example.demo.filiere.Filiere;
import com.example.demo.niveau.Niveau;
import com.example.demo.utilisateur.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "etudiants")
@Getter
@Setter


public class Etudiant extends Utilisateur {
    //Attribution du role
    @Override
    public Role getRole() {
        return Role.ROLE_ETUDIANT;
    }

    @JsonIgnoreProperties({"etudiant", "documents"}) // Ajoutez ici les propriétés à ignorer
    @OneToMany(mappedBy = "etudiant",cascade = CascadeType.ALL)
    private List<Document> documents= new ArrayList<>();

    @OneToOne(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("etudiant") // Au lieu de @JsonManagedReference
    private DossierAdmission dossierAdmission;


    @ManyToOne
    @JsonIgnoreProperties("dossiers")
    @JoinColumn(name = "filiere_id")
    private Filiere filiereSouhaitee;

    @ManyToOne
    @JsonIgnoreProperties("dossiers")
    @JoinColumn(name = "niveau_id")
    private Niveau niveauSouhaite;

 }