package com.example.demo.etudiant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.enums.Role;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    // Ajouter un nouvel étudiant
    @PostMapping("/save")
    public Etudiant ajouterEtudiant(@RequestBody Etudiant etudiant) {
        etudiant.setRole(Role.ROLE_ETUDIANT);
       
        return  etudiantService.ajouterEtudiant(etudiant);
    }

    // Récupérer tous les étudiants
    @GetMapping
    public ResponseEntity<List<Etudiant>> recupererTousLesEtudiants() {
        List<Etudiant> etudiants = etudiantService.recupererTousLesEtudiants();
        return ResponseEntity.ok(etudiants);
    }

    // Récupérer un étudiant par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> recupererEtudiantParId(@PathVariable Long id) {
        return etudiantService.recupererEtudiantParId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Supprimer un étudiant par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> supprimerEtudiant(@PathVariable Long id) {
        etudiantService.supprimerEtudiant(id);
        return ResponseEntity.ok("Étudiant supprimé avec succès.");
    }



    //Verification de l'existence
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkEtudiantExists(@RequestParam String email){
        boolean exists=etudiantService.emailExists(email);
        return ResponseEntity.ok(exists);
    }









}