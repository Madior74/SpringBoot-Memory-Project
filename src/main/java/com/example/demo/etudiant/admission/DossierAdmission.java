package com.example.demo.etudiant.admission;


import com.example.demo.etudiant.prinscription.Etudiant;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "etudiant")

public class DossierAdmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean copieCni;
    private boolean releveNotes;
    private boolean diplome;
    private String remarque;

    private String statut; // EN_ATTENTE, COMPLET, REFUSE

   @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "etudiant_id")
    @JsonIgnoreProperties("dossierAdmission") // Au lieu de @JsonBackReference
    private Etudiant etudiant;

    

}