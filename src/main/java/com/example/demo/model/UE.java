package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomUE;
    private String codeUE;

    @ManyToOne
    @JoinColumn(name = "semestre_id", nullable = false)
    @JsonIgnore
    private Semestre semestre;

    @OneToMany(mappedBy = "ue", cascade = CascadeType.ALL)
    @JsonManagedReference // Côté "maître" de la relation
    private List<CourseModule> modules = new ArrayList<>();
    
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dateAjout;


    //Le nombre toatl de crédit de l'UE
    public double getNbreCredit() {
        return modules.stream()
                .filter(module -> module.getCreditModule() != null)
                .mapToDouble(CourseModule::getCreditModule)
                .sum();
    }



    // Le volume horaire total de l'UE
    public double getVolumeHoraireTotal() {
        return modules.stream()
                .mapToInt(CourseModule::getVolumeHoraire)
                .sum();
    }
    // Le nombre de modules de l'UE
    public int getNbreModules() {
        return modules.size();
    }

    // Méthodes pour ajouter/supprimer des modules
    public void addModule(CourseModule module) {
        modules.add(module);
        module.setUe(this);
    }

    public void removeModule(CourseModule module) {
        modules.remove(module);
        module.setUe(null);
    }
}