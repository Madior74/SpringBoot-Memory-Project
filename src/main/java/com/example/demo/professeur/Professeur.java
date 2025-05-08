package com.example.demo.professeur;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.cours.Cours;
import com.example.demo.enums.Role;
import com.example.demo.utilisateur.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "professeurs")
@Getter
@Setter

public class Professeur extends Utilisateur {

    private String grade; // Exemple : Assistant, Maître de conférences, Professeur titulaire, etc.
    private boolean estPermanent; // Si le prof est permanent ou vacataire

    @ManyToMany
    @JoinTable(name = "professeur_specialites",
           joinColumns = @JoinColumn(name = "professeur_id"),
           inverseJoinColumns = @JoinColumn(name = "specialite_id"))
    private List<com.example.demo.Specialite.Specialite> specialites = new ArrayList<>();



    
    @OneToMany(mappedBy = "professeur")
    @JsonIgnore
    private List<Cours> coursAssures = new ArrayList<>();

    @Override
    public Role getRole(){
        return Role.ROLE_PROFESSEUR;
    }
}
