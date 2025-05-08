package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.anneeAcademique.AnneeAcademique;
import com.example.demo.anneeAcademique.AnneeAcademiqueRepository;

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

  
}
