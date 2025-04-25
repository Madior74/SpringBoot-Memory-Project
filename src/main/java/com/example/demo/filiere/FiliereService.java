package com.example.demo.filiere;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.niveau.Niveau;
import com.example.demo.repository.AnneeAcademiqueRepository;

import lombok.Data;





@Data
@Service
public class FiliereService {
    @Autowired
    private FiliereRepository filiereRepository;

    @Autowired
    private AnneeAcademiqueRepository anneeAcademiqueRepository;

    public List<Filiere> getAllFilieres() {
        return filiereRepository.findAll();
    }

    public Filiere getFiliereById(Long id) {
        return filiereRepository.findById(id).orElseThrow(() -> new RuntimeException("Filière non trouvée"));
    }

    public Filiere saveFiliere(Filiere filiere) {
        return filiereRepository.save(filiere);
    }

    public void deleteFiliere(final Long id) {
        filiereRepository.deleteById(id);
    }

    public List<Niveau> getNiveauxByFiliere(Long filiereId) {
        Filiere filiere = filiereRepository.findById(filiereId)
                .orElseThrow(() -> new RuntimeException("Filière non trouvée"));
        return filiere.getNiveaux();
    }

     //
     public Filiere findById(Long id) {
        return filiereRepository.findById(id).orElse(null);
    }



  
}
        
