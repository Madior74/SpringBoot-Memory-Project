package com.example.demo.Seance;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor


public class SeanceDTO {
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateSeance;

    private LocalTime heureDebut; // Changed to LocalTime
    private LocalTime heureFin;   // Changed to LocalTime

    private boolean estEnLigne;
    private boolean estAnnulee;

    private Long salleId;
    private Long moduleId;
    private Long professeurId;
    private Long anneeAcademiqueId;

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateSeance() {
        return dateSeance;
    }

    public void setDateSeance(LocalDate dateSeance) {
        this.dateSeance = dateSeance;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public boolean isEstEnLigne() {
        return estEnLigne;
    }

    public void setEstEnLigne(boolean estEnLigne) {
        this.estEnLigne = estEnLigne;
    }

    public boolean isEstAnnulee() {
        return estAnnulee;
    }

    public void setEstAnnulee(boolean estAnnulee) {
        this.estAnnulee = estAnnulee;
    }

    public Long getSalleId() {
        return salleId;
    }

    public void setSalleId(Long salleId) {
        this.salleId = salleId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getProfesseurId() {
        return professeurId;
    }

    public void setProfesseurId(Long professeurId) {
        this.professeurId = professeurId;
    }

    public Long getAnneeAcademiqueId() {
        return anneeAcademiqueId;
    }

    public void setAnneeAcademiqueId(Long anneeAcademiqueId) {
        this.anneeAcademiqueId = anneeAcademiqueId;
    }

   
    @Override
    public String toString() {
        return "SeanceDTO{" +
                "id=" + id +
                ", dateSeance=" + dateSeance +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                ", estEnLigne=" + estEnLigne +
                ", estAnnulee=" + estAnnulee +
                ", salleId=" + salleId +
                ", moduleId=" + moduleId +
                ", professeurId=" + professeurId +
                ", anneeAcademiqueId=" + anneeAcademiqueId +   
                '}';
    }

  
}