package com.example.demo.dto;

import lombok.Data;
import java.util.List;

import com.example.demo.model.Niveau;
@Data
public class MaquetteSemestreDTO {
    private String nomSemestre;
    private Niveau niveau; // Changé de String à Niveau
    private String filiere;

gi    private double totalCredits;
    private double totalVolumeHoraire;
    private int nombreModules;

    private List<UEInfo> ues;

    @Data
    public static class UEInfo {
        private String nomUE;
        private String codeUE;
        private List<ModuleInfo> modules;
    }

    @Data
    public static class ModuleInfo {
        private String nomModule;
        private int volumeHoraire;
        private Double creditModule;
    }
}