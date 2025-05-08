package com.example.demo.professeur;


import com.example.demo.Specialite.Specialite;
import com.example.demo.enums.Role;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.example.demo.enums.Role.ROLE_PROFESSEUR;


@RestController
@RequestMapping("/professeurs")
public class ProfesseurController {

    @Autowired
    private ProfesseurService professeurService;

    // Lister tous les professeurs
    @GetMapping
    public List<ProfesseurDTO> getAllProfesseurs() {
        return professeurService.getAllProfesseurs();
    }

    // Obtenir un professeur par ID
    @GetMapping("/{id}")
    public ProfesseurDTO getProfesseurById(@PathVariable Long id) {
        return professeurService.getProfesseurById(id);
    }

    // Créer un nouveau professeur
    @PostMapping("/save")
    public ProfesseurDTO createProfesseur(@RequestBody Professeur professeur) {

        professeur.setRole(ROLE_PROFESSEUR);
        return professeurService.createProfesseur(professeur);
    }

    // Mettre à jour un professeur
    @PutMapping("/{id}")
    public ProfesseurDTO updateProfesseur(
            @PathVariable Long id,
            @RequestBody Professeur professeurDetails) {
        return professeurService.updateProfesseur(id, professeurDetails);
    }

    // Supprimer un professeur
    @DeleteMapping("/{id}")
    public void deleteProfesseur(@PathVariable Long id) {
        professeurService.deleteProfesseur(id);
    }

    // Specialité d'un professeur
    @GetMapping("/{id}/specialites")
    public List<Specialite> getSpecialitesByProfesseurId(@PathVariable Long id) {
        return professeurService.getSpecialitesByProfesseurId(id);
    }



    // Ajouter une spécialité à un professeur
    @PostMapping("/{id}/specialites")
    public void addSpecialitesToProfesseur(
            @PathVariable Long id,
            @RequestBody List<Long> specialiteIds
    ) 
    {
        professeurService.addSpecialitesToProfesseur(id, specialiteIds);
    }


    
    // Supprimer une spécialité d'un professeur
    @DeleteMapping("/{id}/specialites/{specialiteId}")
    public void removeSpecialiteFromProfesseur(@PathVariable Long id, @PathVariable Long specialiteId) {
        professeurService.removeSpecialiteFromProfesseur(id, specialiteId);
    }
    // Obtenir le nombre de professeurs
    @GetMapping("/count")
    public long countProfesseurs() {
        return professeurService.countProfesseurs();
    }
}