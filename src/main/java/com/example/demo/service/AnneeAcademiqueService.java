package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AnneeAcademique;
import com.example.demo.repository.AnneeAcademiqueRepository;

import lombok.Data;

@Data
@Service
public class AnneeAcademiqueService {
    @Autowired
    private AnneeAcademiqueRepository academiqueRepository;

    public Optional <AnneeAcademique> getAnneeAcademique(final Long id){
        return academiqueRepository.findById(id);
        
    }
    //Get All
    public List <AnneeAcademique> getAllAnneeAcademique(){
        return academiqueRepository.findAll();
    }
    //Create and Update

    public AnneeAcademique creategetAnneeAcademique(AnneeAcademique anneeAcademique) {
        return academiqueRepository.save(anneeAcademique);
    }
    //delete

    public void deletegetAnneeAcademique(final Long id){
        academiqueRepository.deleteById(id);

    }

}
