package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.filiere.Filiere;
import com.example.demo.model.Domaine;
import com.example.demo.model.Specialite;
import com.example.demo.niveau.Niveau;
import com.example.demo.service.DomaineService;
import com.example.demo.service.SpecialiteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/specialites")
public class SpecialiteController {
    @Autowired
    private SpecialiteService specialiteService;

    @Autowired
    private DomaineService domaineService;



    //Ajouter une Specialite 
   
    @PostMapping("/domaine/{domaineId}")
    public ResponseEntity<?> addNiveauToFiliere(@PathVariable Long domaineId, @RequestBody Specialite specialiteDetail) {
    // Vérifier si la filière existe
    Domaine domaine = domaineService.findById(domaineId);
    if (domaine == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Domaine non trouvée");
    }

    // Vérifier si le niveau existe déjà dans cette filière
    boolean specialiteExiste = specialiteService.existsBySpecialiteAndDomaine(specialiteDetail.getNomSpecialite(), domaineId);
    if (specialiteExiste) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Cet Specialite existe déjà dans ce  domaine");
    }

    // Créer le niveau
    specialiteDetail.setDomaine(domaine);
    Specialite savedNiveau = specialiteService.ajouterSpecialite(specialiteDetail);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedNiveau);
   }

    //Recuperer toutes les specialites
    @GetMapping
    public List<Specialite> getAllSpecialites(){
        return specialiteService.getAllSpecialite();
    }
    
   
    //Recuperer un diplome par son Id
    @GetMapping("/{id}")
    public Specialite getDiplomeById(@PathVariable("id") Long id){
        return specialiteService.getDiplomeById(id);
    }



// //Pour recuperer les specialite d'un domaine 

   //Niveaux pour chaque filiere

   @GetMapping("/domaine/{domaineId}")
   public List<Specialite> getSpecialiteByDomaine(@PathVariable Long domaineId) {
       return specialiteService.getSpecialiteByDomaine(domaineId);
   }
// @GetMapping("/{specialiteId}/domaine")
// public ResponseEntity<Domaine> getSpecialiteByDomaineId(@PathVariable Long domaineId) {
//     // Récupérer le niveau
//     Specialite spec = specialiteService.getDiplomeById(domaineId);
//     if (spec == null) {
//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//     }

//     // Récupérer la filière associée au niveau
//     Domaine domaine = spec.getDomaine();
//     if (domaine == null) {
//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//     }

//     return ResponseEntity.ok(domaine);
// }
    

    @DeleteMapping("/{id}")
    public void deleteSpecialite(@PathVariable("id") Long id){
        specialiteService.deleteSpecialite(id);
    }


    ///Verification de l'existence
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkSpecialiteExists( @RequestParam String nomSpecialite,@RequestParam Long domaineId){
        boolean exists=specialiteService.existsBySpecialiteAndDomaine(nomSpecialite, domaineId);

        return ResponseEntity.ok(exists);
    }

}
