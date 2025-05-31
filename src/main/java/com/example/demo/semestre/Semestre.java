package com.example.demo.semestre;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.Seance.Seance;
import com.example.demo.niveau.Niveau;
import com.example.demo.ue.UE;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
public class Semestre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomSemestre;

    @ManyToOne
    @JoinColumn(name = "niveau_id", nullable = false)
    private Niveau niveau;


    @OneToMany(mappedBy = "semestre", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UE> ues = new ArrayList<>();

    // @OneToMany(mappedBy = "semestre", cascade = CascadeType.ALL)
    // @JsonIgnore
    // private List<Seance> seances = new ArrayList<>();

   

    public String getNomFiliere() {
        if (this.niveau != null && this.niveau.getFiliere() != null) {
            return this.niveau.getFiliere().getNomFiliere();
        }
        return null; // Ou une valeur par défaut si nécessaire
    }

}
