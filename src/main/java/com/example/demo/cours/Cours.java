package com.example.demo.cours;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.coursModule.CourseModule;
import com.example.demo.filiere.Filiere;
import com.example.demo.niveau.Niveau;
import com.example.demo.professeur.Professeur;
import com.example.demo.semestre.Semestre;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Professeur professeur;

    @ManyToOne(optional = false)
    private CourseModule module;

    @ManyToOne(optional = false)
    private Filiere filiere;

    @ManyToOne(optional = false)
    private Niveau niveau;

    @ManyToOne(optional = false)
    private Semestre semestre;

    private String salle; // facultatif
    private String jour; // par ex. "Lundi"
    private String heureDebut; // ex: "08:00"
    private String heureFin;   // ex: "10:00"

    private LocalDate dateDebut;
    private LocalDate dateFin;

    private Boolean estRemplacement = false; // Pour savoir si c’est un remplacement temporaire

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateAjout;
}
