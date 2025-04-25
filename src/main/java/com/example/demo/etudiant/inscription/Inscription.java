package com.example.demo.etudiant.inscription;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.demo.etudiant.Etudiant;
import com.example.demo.filiere.Filiere;
import com.example.demo.model.AnneeAcademique;
import com.example.demo.niveau.Niveau;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties
    private Etudiant etudiant;

    @ManyToOne(optional = false)
    private Filiere filiere;


    @ManyToOne(optional = false)
    private Niveau niveau;

    @ManyToOne(optional = false)
    private AnneeAcademique anneeAcademique;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateInscription;

    private String montantVerse;




}
