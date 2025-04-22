package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.InscriptionRequestDTO;
import com.example.demo.model.Inscription;
import com.example.demo.repository.InscriptionRepository;
import com.example.demo.service.InscriptionService;


@RestController
@RequestMapping("/inscriptions")
public class InscriptionController {


    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private InscriptionRepository inscriptionRepository;

    //Recuperer toutes les inscriptions
    @GetMapping
    public List<Inscription> getAllInscriptions(){
        return inscriptionRepository.findAll();
    }

    //Creer un nouveau Etudint
    @PostMapping("/save")
//    public Inscription nouvelleInscription(@RequestBody Inscription inscriptionDetail){
//            return inscriptionService.createInscription(inscriptionDetail);
//    }
    public ResponseEntity<?> inscrireEtudiant(@RequestBody Inscription inscription) {
        try {
            Inscription saved = inscriptionService.ajouterInscription(inscription);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", e.getMessage()));
        }
    }





    //Supprimer une inscription
    @DeleteMapping("/{id}")
    public void deleteInscription(@PathVariable Long id){
        inscriptionRepository.deleteById(id);
    }
}
