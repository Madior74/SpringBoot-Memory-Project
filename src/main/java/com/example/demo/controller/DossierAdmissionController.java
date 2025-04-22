package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.DossierAdmission;
import com.example.demo.repository.DossierAdmissionRepository;
import com.example.demo.service.DossierAdmissionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/dossiers")
public class DossierAdmissionController {

    @Autowired
    private DossierAdmissionService dossierAdmissionService;

    @Autowired
    private DossierAdmissionRepository dossierAdmissionRepository;


    //Recuperer tous les dossiers
    @GetMapping
    public List<DossierAdmission> getAllDossierAdmission(){
        return dossierAdmissionService.getAllDossierAdmission();
    }

    //Dossier par Id
    @GetMapping("/{id}")
    public DossierAdmission getDossierAdmissionById(@PathVariable Long id){
        return dossierAdmissionService.getDossierAdmissionById(id);
    }
    


    //Creer un nouveau dossier
    @PostMapping("/save")
    public DossierAdmission createDossierAdmission(@RequestBody DossierAdmission dossierAdmission) {
        Long etudiantId = dossierAdmission.getEtudiant().getId();
        return dossierAdmissionService.createDossier(dossierAdmission, etudiantId);
    }
    
    // public ResponseEntity<DossierAdmission> createDossierAdmission(@RequestBody DossierAdmission dossierAdmission) {
    //     try {
    //         // Log du JSON reçu
    //         System.out.println("JSON reçu : " + new ObjectMapper().writeValueAsString(dossierAdmission));
    
    //         // Sauvegarde via le service
    //         DossierAdmission savedDossierAdmission = dossierAdmissionService.addDossierAdmission(dossierAdmission);
    //         return ResponseEntity.status(HttpStatus.CREATED).body(savedDossierAdmission);
    //     } catch (JsonProcessingException e) {
    //         // Erreur spécifique au parsing JSON
    //         System.err.println("Erreur de parsing JSON : " + e.getMessage());
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    //     } catch (Exception e) {
    //         // Toutes les autres erreurs
    //         System.err.println("Erreur inattendue : " + e.getMessage());
    //         e.printStackTrace();
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    //     }
    // }


    //Existence
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkDossierExists(@RequestParam Long etudiantId){
        boolean exists=dossierAdmissionService.existsByEtudiant(etudiantId);
        return  ResponseEntity.ok(exists);
    }


    // //Supprimer un dossier
    // @DeleteMapping("/{id}")
    // public void deleteDossier(@PathVariable Long id){
    //     dossierAdmissionRepository.deleteById(id);
    // }


    //supprime d’abord la référence dans l'étudiant, ensuite  supprime le dossier 
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteDossier(@PathVariable Long id){
        Optional<DossierAdmission> dossierOptional = dossierAdmissionRepository.findById(id);
    
        if (!dossierOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dossier non trouvé");
        }
    
        DossierAdmission dossier = dossierOptional.get();
    
        if (dossier.getEtudiant() != null) {
            dossier.getEtudiant().setDossierAdmission(null);
        }
    
        dossierAdmissionRepository.delete(dossier);
    
        return ResponseEntity.ok("Dossier supprimé");
    }
    
}
   
