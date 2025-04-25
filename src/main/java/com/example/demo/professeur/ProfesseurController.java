package com.example.demo.professeur;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/professeurs")
public class ProfesseurController {
    @Autowired
    private  ProfesseurService professeurService;

    //Recuperer tous les profs
    @GetMapping
    public List<Professeur> getAllProfesseurs(){
        return professeurService.getAllProfesseurs();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Professeur> getProfesseurById(@PathVariable Long id){
        Professeur professeur=professeurService.getProfesseurById(id);
        return professeur !=null? ResponseEntity.ok().body(professeur):ResponseEntity.notFound().build();
    }



@PostMapping("/save")
public Professeur createProfesseur(@RequestBody Professeur professeur, 
                                 @RequestParam(required = false) List<Long> modulesIds) {
    return professeurService.createProfesseur(professeur, modulesIds);
}




    //Mette à jour un prof
    @PostMapping("/{id}")
    public ResponseEntity<Professeur> updateProfesseur(@PathVariable Long id,@RequestBody Professeur professeurDetails,@RequestParam(required = false) List<Long> modulesIds){
        Professeur professeur=professeurService.updateProfesseur(id, professeurDetails, modulesIds);
        return professeur !=null? ResponseEntity.ok().body(professeur):ResponseEntity.notFound().build();
    }
   
    
    
    //Supprimer un prof
    @DeleteMapping("/{id}")
    public ResponseEntity<Professeur> deleteProfesseur(@PathVariable Long id){
        professeurService.deleteProfesseur(id);
        return ResponseEntity.noContent().build();
    }
    
}
