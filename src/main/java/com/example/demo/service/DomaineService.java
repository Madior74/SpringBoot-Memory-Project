package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Domaine;
import com.example.demo.repository.DomaineRepository;

import lombok.Data;

@Service
@Data
public class DomaineService {

    @Autowired
    private DomaineRepository domaineRepository;


    //Nouveau Domaine 
    public Domaine createDomaine(Domaine domaine ){
        return domaineRepository.save(domaine);
    }

    //Recuperr tous les domaines
    public List<Domaine> getAllDomaine(){
        return domaineRepository.findAll();
    }

    //Supprimer un domaine
    public void deleteDomaine( Long id){
        domaineRepository.deleteById(id);
    }

    //Mettre a jour un domaine
    public Domaine updateDomaine(Domaine domaine){
        return domaineRepository.save(domaine);
    }


    //Existence du domaine
    
    
    //Veification de l'existence 
    public boolean domaineExists(String nomNiveau) {
        return domaineRepository.existsByNomDomaine(nomNiveau);
    }


    //

    public Domaine findById( final Long id){
        return domaineRepository.findById(id).orElse(null);
    }
}
