package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Domaine;
import com.example.demo.service.DomaineService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/domaines")
public class DomaineController {
    
    @Autowired
    private DomaineService domaineService;

    //Recuperer tous les domaines
    @GetMapping
    public List<Domaine> getAllDomaines(){
        return domaineService.getAllDomaine();
    }


    //Ajouter un domaine
    @PostMapping("/save")
    public Domaine nouveauDomaine(@RequestBody Domaine domaine){
        return domaineService.createDomaine(domaine);
    }

    //Mettre a jour un domaine
    @PutMapping("/{id}")
    public Domaine updateDomaine(Domaine domaine){
        return domaineService.updateDomaine(domaine);
    }
    
    //Supprimer un domaine
    @DeleteMapping("/{id}")
    public void deleteDomaine(@PathVariable Long id){
        domaineService.deleteDomaine(id);
    }

    //verificatio

     @GetMapping("/exists")
    public ResponseEntity<Boolean> checkDomaineExists(@RequestParam String nomDomaine  ) {
        boolean exists = domaineService.domaineExists(nomDomaine);
        return ResponseEntity.ok(exists);
    }

    
}
