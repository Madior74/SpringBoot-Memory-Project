package com.example.demo.anneeAcademique;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.AnneeAcademiqueService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/annees")
public class AnneeAcademiqueController {

    @Autowired
    private AnneeAcademiqueService anneeAcademiqueService;

   @PostMapping("/save")
    public ResponseEntity<AnneeAcademique> creategetAnneeAcademique(@RequestBody AnneeAcademique anneeAcademique) {
    if (anneeAcademique.getNomAnnee() == null || anneeAcademique.getNomAnnee().isEmpty()) {
        return ResponseEntity.badRequest().build(); // Retourne une erreur 400 si nomSession est nul ou vide
    }
    AnneeAcademique createdSession = anneeAcademiqueService.creategetAnneeAcademique(anneeAcademique);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdSession);
}


    @GetMapping("/{id}")
    public ResponseEntity<AnneeAcademique> getAnneeAcademique(@PathVariable Long id) {
        Optional<AnneeAcademique> session = anneeAcademiqueService.getAnneeAcademique(id);
        return session.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<AnneeAcademique> getAllAnneeAcademique() {
        return anneeAcademiqueService.getAllAnneeAcademique();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnneeAcademique> updateAnnee(@PathVariable Long id, @RequestBody AnneeAcademique session) {
        // Mettez à jour la session ici
        // Vous devez d'abord vérifier si la session existe
        if (anneeAcademiqueService.getAnneeAcademique(id).isPresent()) {
            session.setId(id); // Assurez-vous que l'ID est défini
            return ResponseEntity.ok(anneeAcademiqueService.creategetAnneeAcademique(session));
        }
        return ResponseEntity.notFound().build();
    }


    //Suppression d'une Annee
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnee(@PathVariable Long id) {
        anneeAcademiqueService.deletegetAnneeAcademique(id);
        return ResponseEntity.noContent().build();
    }

    // 

}