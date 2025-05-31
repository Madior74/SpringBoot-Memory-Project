package com.example.demo.etudiant.inscription;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



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

    //Methode Existe ou NOn
     @GetMapping("/check")
    public boolean checkIfInscriptionExists(
            @RequestParam Long etudiantId,
            @RequestParam Long filiereId,
            @RequestParam Long anneeAcademiqueId) {
        return inscriptionService.checkIfInscriptionExists(etudiantId, filiereId, anneeAcademiqueId);
    }
    

    //Recuperer les inscriptions par niveau
    @GetMapping("/niveau/{niveauId}")
public List<Inscription> getInscriptionsByNiveau(@PathVariable Long niveauId) {
    return inscriptionService.getInscriptionsByNiveauId(niveauId);
}

    // //Recuperer les inscriptions par etudiant
    // @GetMapping("/etudiant/{etudiantId}")
    // public List<Inscription> getInscriptionsByEtudiant(@PathVariable Long etudiantId) {
    //     return inscriptionRepository.findByEtudiantId(etudiantId);
    // }

    // //Recuperer les inscriptions par filiere
    // @GetMapping("/filiere/{filiereId}")
    // public List<Inscription> getInscriptionsByFiliere(@PathVariable Long filiereId) {
    //     return inscriptionRepository.findByFiliereId(filiereId);
    // }
}
