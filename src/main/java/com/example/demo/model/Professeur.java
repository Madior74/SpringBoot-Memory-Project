package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "professeurs")
@Getter
@Setter
public class Professeur extends Utilisateur {

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
        name = "professeur_specialite",
        joinColumns = @JoinColumn(name = "professeur_id"),
        inverseJoinColumns = @JoinColumn(name = "specialite_id")
    )
    @JsonIgnoreProperties("professeurs")
    private List<Specialite> specialites;
}