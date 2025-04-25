package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.coursModule.CourseModule;
import com.example.demo.dto.MaquetteSemestreDTO;
import com.example.demo.semestre.Semestre;
import com.example.demo.semestre.SemestreRepository;
import com.example.demo.ue.UE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaquetteService {

    @Autowired
    private SemestreRepository semestreRepository;

    public MaquetteSemestreDTO generateMaquetteSemestre(Long semestreId) {
        // Récupérer le semestre depuis la base de données
        Semestre semestre = semestreRepository.findById(semestreId)
                .orElseThrow(() -> new RuntimeException("Semestre non trouvé"));

        // Logique pour générer la maquette (déjà implémentée précédemment)
        return generateMaquetteSemestre(semestre);
    }

  public MaquetteSemestreDTO generateMaquetteSemestre(Semestre semestre) {
    // Initialiser le DTO principal
    MaquetteSemestreDTO maquette = new MaquetteSemestreDTO();
    
    // Remplir les informations du semestre
    maquette.setNomSemestre(semestre.getNomSemestre());
    maquette.setNiveau(semestre.getNiveau());
    maquette.setFiliere(semestre.getNomFiliere());

    // Liste des UEInfo pour le semestre
    List<MaquetteSemestreDTO.UEInfo> uesInfo = semestre.getUes().stream()
            .map(this::mapUEToUEInfo)
            .collect(Collectors.toList());

    // Calculer les totaux globaux pour le semestre
    double totalCredits = uesInfo.stream()
            .flatMap(ueInfo -> ueInfo.getModules().stream())
            .mapToDouble(moduleInfo -> moduleInfo.getCreditModule() != null ? moduleInfo.getCreditModule() : 0)
            .sum();

    double totalVolumeHoraire = uesInfo.stream()
            .flatMap(ueInfo -> ueInfo.getModules().stream())
            .mapToInt(MaquetteSemestreDTO.ModuleInfo::getVolumeHoraire)
            .sum();

    int nombreModules = uesInfo.stream()
            .mapToInt(ueInfo -> ueInfo.getModules().size())
            .sum();

    // Affecter les valeurs calculées au DTO
    maquette.setTotalCredits(totalCredits);
    maquette.setTotalVolumeHoraire(totalVolumeHoraire);
    maquette.setNombreModules(nombreModules);
    maquette.setUes(uesInfo);

    return maquette;
}

// Méthode pour mapper une UE vers UEInfo
private MaquetteSemestreDTO.UEInfo mapUEToUEInfo(UE ue) {
    MaquetteSemestreDTO.UEInfo ueInfo = new MaquetteSemestreDTO.UEInfo();
    ueInfo.setNomUE(ue.getNomUE());
    ueInfo.setCodeUE(ue.getCodeUE());

    // Mapper les modules de l'UE
    List<MaquetteSemestreDTO.ModuleInfo> modulesInfo = ue.getModules().stream()
            .map(this::mapModuleToModuleInfo)
            .collect(Collectors.toList());

    ueInfo.setModules(modulesInfo);
    return ueInfo;
}

// Méthode pour mapper un CourseModule vers ModuleInfo
private MaquetteSemestreDTO.ModuleInfo mapModuleToModuleInfo(CourseModule module) {
    MaquetteSemestreDTO.ModuleInfo moduleInfo = new MaquetteSemestreDTO.ModuleInfo();
    moduleInfo.setNomModule(module.getNomModule());
    moduleInfo.setVolumeHoraire(module.getVolumeHoraire());
    moduleInfo.setCreditModule(module.getCreditModule());
    return moduleInfo;
}
}