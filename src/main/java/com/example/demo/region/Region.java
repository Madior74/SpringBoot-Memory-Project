package com.example.demo.region;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.region.departement.Departement;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Region {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomRegion;

    @JsonIgnoreProperties("region")  // Spécifiez la propriété à ignorer
    @OneToMany(mappedBy = "region" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Departement> departements = new ArrayList<>();

    
    
    
}
