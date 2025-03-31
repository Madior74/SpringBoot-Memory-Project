package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double noteDevoir;  
    private Double noteExamen;  
    private Double moyenne;
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateAjout;    

    @ManyToOne
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "professeur_id", nullable = false)
    private Professeur professeur;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = false)
    private CourseModule module;

    public void calculerMoyenne() {
        if (noteDevoir != null && noteExamen != null) {
            this.moyenne = (noteDevoir * 0.3) + (noteExamen * 0.7);
        } else {
            this.moyenne = null; 
        }
    }

    public void setNoteDevoir(Double noteDevoir) {
        this.noteDevoir = noteDevoir;
        calculerMoyenne();
    }

    public void setNoteExamen(Double noteExamen) {
        this.noteExamen = noteExamen;
        calculerMoyenne();
    }
}
