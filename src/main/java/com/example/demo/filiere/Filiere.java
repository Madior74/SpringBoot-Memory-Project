package com.example.demo.filiere;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.niveau.Niveau;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Data;
@Entity
@Table(name = "filieres")
@Data
public class Filiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomFiliere;
    private String description;
    private boolean actif = true;

    // // --- Listes ---
    // @ElementCollection
    // @CollectionTable(name = "filiere_debouches", joinColumns = @JoinColumn(name = "filiere_id"))
    // @Column(name = "debouche")
    // private List<String> debouches = new ArrayList<>();

    // @ElementCollection
    // @CollectionTable(name = "filiere_prerequis", joinColumns = @JoinColumn(name = "filiere_id"))
    // @Column(name = "prerequis")
    // private List<String> prerequis = new ArrayList<>();

    // @ElementCollection
    // @CollectionTable(name = "filiere_admission", joinColumns = @JoinColumn(name = "filiere_id"))
    // @Column(name = "critere_admission")
    // private List<String> admission = new ArrayList<>();
    // // -----------------------------

    @OneToMany(mappedBy = "filiere", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Niveau> niveaux = new ArrayList<>();

    // Méthodes utilitaires (optionnelles mais utiles pour la cohérence des relations)
    public void addNiveau(Niveau niveau) {
        niveaux.add(niveau);
        niveau.setFiliere(this);
    }

    public void removeNiveau(Niveau niveau) {
        niveaux.remove(niveau);
        niveau.setFiliere(null);
    }

   
  
    // L'annotation mappedBy = "filieres" indique que Filiere est le côté inverse de la relation, 
    // et que la configuration principale est définie dans l'autre.

}
    