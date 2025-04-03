package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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

    @ManyToOne
    @JoinColumn( nullable = false) // Un module doit forcément  appartenir à une UE
    @JsonIgnore
    private UE ue;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Devoir> devoirNotes = new ArrayList<>();



































    // // Constructeurs
    // public CourseModule() {}

    // public CourseModule(String nomModule, int volumeHoraire, Double creditModule, UE ue) {
    //     this.nomModule = nomModule;
    //     this.volumeHoraire = volumeHoraire;
    //     this.creditModule = creditModule;
    //     this.ue = ue;
    //     this.dateAjout = LocalDateTime.now();
    // }

    // // Getters et Setters
    // public Long getId() { return id; }
    // public void setId(Long id) { this.id = id; }

    // public String getNomModule() { return nomModule; }
    // public void setNomModule(String nomModule) { this.nomModule = nomModule; }

    // public int getVolumeHoraire() { return volumeHoraire; }
    // public void setVolumeHoraire(int volumeHoraire) { this.volumeHoraire = volumeHoraire; }

    // public Double getCreditModule() { return creditModule; }
    // public void setCreditModule(Double creditModule) { this.creditModule = creditModule; }

    // public UE getUe() { return ue; }
    // public void setUe(UE ue) { this.ue = ue; }

    // public List<Note> getNotes() { return notes; }
    // public void setNotes(List<Note> notes) { this.notes = notes; }

    // public LocalDateTime getDateAjout() { return dateAjout; }
    // public void setDateAjout(LocalDateTime dateAjout) { this.dateAjout = dateAjout; }
}