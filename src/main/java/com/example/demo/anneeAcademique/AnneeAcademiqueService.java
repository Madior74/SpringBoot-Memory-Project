package com.example.demo.anneeAcademique;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
public class AnneeAcademiqueService {
    @Autowired
    private AnneeAcademiqueRepository anneeAcademiqueRepository;

    public Optional <AnneeAcademique> getAnneeAcademique(final Long id){
        return anneeAcademiqueRepository.findById(id);
        
    }
    //Get All
    public List <AnneeAcademique> getAllAnneeAcademique(){
        return anneeAcademiqueRepository.findAll();
    }
    //Create and Update
    public AnneeAcademique creategetAnneeAcademique(AnneeAcademique anneeAcademique) {
        return anneeAcademiqueRepository.save(anneeAcademique);
    }
    //delete

    public void deletegetAnneeAcademique(final Long id){
        anneeAcademiqueRepository.deleteById(id);
    }

    //l'annne En cours
     public AnneeAcademique getAnneeAcademiqueEnCours() {
        return anneeAcademiqueRepository.findCurrent(LocalDate.now())
                .orElseThrow(() -> new RuntimeException("Aucune année académique en cours."));
    }
}
