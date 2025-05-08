package com.example.demo.etudiant.inscription;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.demo.anneeAcademique.AnneeAcademique;
import com.example.demo.etudiant.prinscription.Etudiant;
import com.example.demo.filiere.Filiere;
import com.example.demo.niveau.Niveau;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    @Column(precision = 10, scale = 2)
    private BigDecimal montantVerse;





}
