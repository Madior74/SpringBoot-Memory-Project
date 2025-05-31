package com.example.demo.Seance;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Salle.Salle;
import com.example.demo.anneeAcademique.AnneeAcademique;
import com.example.demo.coursModule.CourseModule;
import com.example.demo.note.Examen.Examen;
import com.example.demo.note.devoir.Devoir;
import com.example.demo.professeur.Professeur;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name = "seances")
public class Seance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateSeance;


    private LocalTime heureDebut; // Changed to LocalTime
    private LocalTime heureFin;
    private boolean estEnLigne;
    private boolean estAnnulee;
    
    @ManyToOne
    @JoinColumn(name = "salle_id", nullable = true)
    @JsonIgnoreProperties({"seances"})
    private Salle salle;


    @ManyToOne
    @JsonIgnoreProperties({"seances"})
    private CourseModule module;

    @ManyToOne
    @JsonIgnoreProperties({"seances"})
    private Professeur professeur;

    @ManyToOne
    @JsonIgnoreProperties({"seances"})
    private AnneeAcademique anneeAcademique;

    @OneToMany(mappedBy = "seance", cascade = CascadeType.ALL)
    private List<Devoir> devoirs = new ArrayList<>();

    @OneToMany(mappedBy = "seance", cascade = CascadeType.ALL)
    private List<Examen> examens = new ArrayList<>();

    // Durée en heures
    public int getDureeEnHeures() {
        return (int) Duration.between(heureDebut, heureFin).toHours();
    }




    public boolean isDeroulee() {
        if (this.estAnnulee) {
            return false;
        }
        if (this.dateSeance == null || this.heureFin == null) {
            return false;
        }

        LocalDateTime finSeance = LocalDateTime.of(this.dateSeance, this.heureFin);
        return finSeance.isBefore(LocalDateTime.now());
    }

    public boolean isProgrammee() {
        return !this.estAnnulee &&
                this.dateSeance != null &&
                this.dateSeance.isAfter(LocalDate.now());
    }

    public boolean isEnCours() {
        if (this.estAnnulee || this.dateSeance == null ||
                this.heureDebut == null || this.heureFin == null) {
            return false;
        }

        LocalDateTime maintenant = LocalDateTime.now();
        LocalDateTime debut = LocalDateTime.of(this.dateSeance, this.heureDebut);
        LocalDateTime fin = LocalDateTime.of(this.dateSeance, this.heureFin);

        return maintenant.isAfter(debut) && maintenant.isBefore(fin);
    }

    
}
