package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
@Entity
public class UE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomUE;
    private String codeUE;
    private int nbreCredit;

    @ManyToOne
    @JoinColumn(name = "semestre_id", nullable = false)
    @JsonIgnore
    private Semestre semestre;

    @OneToMany(mappedBy = "ue", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseModule> modules = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dateAjout;

    // Constructeurs
    public UE() {}

    public UE(String nomUE, String codeUE, int nbreCredit, Semestre semestre) {
        this.nomUE = nomUE;
        this.codeUE = codeUE;
        this.nbreCredit = nbreCredit;
        this.semestre = semestre;
        this.dateAjout = LocalDateTime.now();
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomUE() { return nomUE; }
    public void setNomUE(String nomUE) { this.nomUE = nomUE; }

    public String getCodeUE() { return codeUE; }
    public void setCodeUE(String codeUE) { this.codeUE = codeUE; }

    public int getNbreCredit() { return nbreCredit; }
    public void setNbreCredit(int nbreCredit) { this.nbreCredit = nbreCredit; }

    public Semestre getSemestre() { return semestre; }
    public void setSemestre(Semestre semestre) { this.semestre = semestre; }

    public List<CourseModule> getModules() { return modules; }
    public void setModules(List<CourseModule> modules) { this.modules = modules; }

    public LocalDateTime getDateAjout() { return dateAjout; }
    public void setDateAjout(LocalDateTime dateAjout) { this.dateAjout = dateAjout; }

    // Méthode pour ajouter un module
    public void addModule(CourseModule module) {
        modules.add(module);
        module.setUe(this); // Associer le module à cette UE
    }

    // Méthode pour supprimer un module
    public void removeModule(CourseModule module) {
        modules.remove(module);
        module.setUe(null); // Dissocier le module de cette UE
    }
}