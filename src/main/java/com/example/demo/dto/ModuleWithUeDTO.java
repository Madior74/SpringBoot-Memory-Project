package com.example.demo.dto;

import lombok.Getter;

@Getter
public class ModuleWithUeDTO {
    private Long id;
    private String nomModule;
    private int volumeHoraire;
    private Double creditModule;
    private String nomUE; // Only include what you need
    
    // Constructor
    public ModuleWithUeDTO(Long id, String nomModule, int volumeHoraire, Double creditModule, String nomUE) {
        this.id = id;
        this.nomModule = nomModule;
        this.volumeHoraire = volumeHoraire;
        this.creditModule = creditModule;
        this.nomUE = nomUE;
    }
    
    // Getters
}