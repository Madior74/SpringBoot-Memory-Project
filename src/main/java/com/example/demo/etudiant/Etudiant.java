package com.example.demo.etudiant;



import java.util.ArrayList;
import java.util.List;

import com.example.demo.etudiant.admission.DossierAdmission;
import com.example.demo.etudiant.document.Document;
import com.example.demo.model.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "etudiants")
@Getter
@Setter


public class Etudiant extends Utilisateur {

    @JsonIgnoreProperties({"etudiant", "documents"}) // Ajoutez ici les propriétés à ignorer
    @OneToMany(mappedBy = "etudiant",cascade = CascadeType.ALL)
    private List<Document> documents= new ArrayList<>();

    @OneToOne(mappedBy = "etudiant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("etudiant") // Au lieu de @JsonManagedReference
    private DossierAdmission dossierAdmission;
}