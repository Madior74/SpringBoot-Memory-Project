package com.example.demo.coursModule;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.etudiant.prinscription.Etudiant;
import com.example.demo.note.Examen;
import com.example.demo.note.Note;
import com.example.demo.note.devoir.Devoir;
import com.example.demo.ue.UE;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "module")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomModule;
    private int volumeHoraire;
    private Double creditModule;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dateAjout;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ue_id")
    @JsonBackReference // Important pour casser la circularité
    private UE ue;
    
    @OneToMany(mappedBy = "courseModule", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Devoir> devoirNotes = new ArrayList<>();

    @OneToMany(mappedBy = "courseModule", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Examen> examenNotes = new ArrayList<>();

    // Méthode pour calculer la moyenne finale d'un étudiant spécifique
    public Double calculateFinalAverage(Etudiant etudiant) {
        // Filtrer les notes de l'étudiant
        double averageDevoir = devoirNotes.stream()
                .filter(note -> note.getEtudiant().equals(etudiant))
                .mapToDouble(Note::getNote)
                .average()
                .orElse(0.0);

        double averageExamen = examenNotes.stream()
                .filter(note -> note.getEtudiant().equals(etudiant))
                .mapToDouble(Note::getNote)
                .average()
                .orElse(0.0);

        // Calcul de la moyenne finale
        return (averageDevoir * 0.3) + (averageExamen * 0.7);
    }

    // Méthode pour vérifier si un étudiant obtient les crédits
    public boolean isCreditObtained(Etudiant etudiant) {
        Double finalAverage = calculateFinalAverage(etudiant);
        return finalAverage != null && finalAverage >= 10.0;
    }
}