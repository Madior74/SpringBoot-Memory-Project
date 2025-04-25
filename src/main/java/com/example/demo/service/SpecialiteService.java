package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Specialite;
import com.example.demo.niveau.Niveau;
import com.example.demo.repository.DomaineRepository;
import com.example.demo.repository.SpecialiteRepository;

import lombok.Data;

@Service
@Data
public class SpecialiteService {


    @Autowired
    private SpecialiteRepository specialiteRepository;

    @Autowired
    private DomaineRepository domaineRepository;

    //Ajouter une Specialite
    public Specialite ajouterSpecialite(Specialite speci){
        return specialiteRepository.save(speci);
    }

    //recuperer par Id
    public Specialite getSpecialiteById(Long id){
        return specialiteRepository.findById(id).orElseThrow(() -> new RuntimeException("Specialite non Trouve"));
    }


    ///recuper tous les diplomes
    public List<Specialite> getAllSpecialite(){
        return specialiteRepository.findAll();
    }


    //Recuper diplome par id
    public Specialite getDiplomeById(Long id){
        return specialiteRepository.findById(id).orElseThrow(()-> new RuntimeException("Diplome non Trouve"));
    }


    //recuperer les specialites par domaine
     public List<Specialite> getSpecialiteByDomaine(Long domaineId) {
        return specialiteRepository.findByDomaineId(domaineId);
    }






    //Supprimer 
    public void deleteSpecialite(Long id){
        specialiteRepository.deleteById(id);
    }



    //Existence 
    public boolean existsBySpecialiteAndDomaine(String nomSpecialite,Long domaineId){
        return specialiteRepository.existsByNomSpecialiteAndDomaineId(nomSpecialite,domaineId);
    }
    
}
