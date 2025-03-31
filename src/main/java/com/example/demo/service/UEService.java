package com.example.demo.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CourseModule;
import com.example.demo.model.UE;
import com.example.demo.repository.UeRepository;


@Service
public class UEService {
    @Autowired
    private UeRepository ueRepository;

    public List<UE> getAllUEs() {
        return ueRepository.findAll();
    }

    public UE getUEBy(Long id) {
        return ueRepository.findById(id).orElseThrow(() -> new RuntimeException("UE non trouvée"));
    }

    public Optional<UE> getUEById(Long ueId) {
        return ueRepository.findById(ueId); // Assurez-vous que le repository retourne Optional
    }
    
    public UE saveUE(UE ue) {
        // Vérifie si l'UE a bien été associée à un semestre
        if ( ue.getSemestre() == null) {
            throw new IllegalArgumentException(" Semestre manquant");
        }
        return ueRepository.save(ue);
    }

    public UE addModuleToUE(Long ueId, CourseModule module) {
        UE ue = ueRepository.findById(ueId).orElse(null);
        if (ue != null) {
            ue.addModule(module); // Appel de la méthode que vous avez définie dans la classe UE
            return ueRepository.save(ue); // Enregistrement des modifications
        }
        return null; // UE non trouvée
    }

    public void deleteUE(Long id) {
        ueRepository.deleteById(id);
    }

   

    //recuperer les UES de chaque Semestre

    public List<UE> getUEBySemestre(Long semestreId){
        return ueRepository.findBySemestreId(semestreId);
    }

        ///Verification de l'existence d'une UE
        public boolean ueExist(String nomUE,Long semestreId){
            return ueRepository.existsByNomUEAndSemestreId(nomUE,semestreId);
        }
    }
