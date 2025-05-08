package com.example.demo.Specialite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/specialites")
public class SpecialiteController {
    @Autowired
    private SpecialiteService specialiteService;

   



    //Ajouter une Specialite 
   
    @PostMapping("/save")
    public ResponseEntity<Specialite> ajouterSpecialite(@RequestBody Specialite specialite){
        Specialite savedSpecialite = specialiteService.ajouterSpecialite(specialite);
        return new ResponseEntity<>(savedSpecialite, HttpStatus.CREATED);
    }
    //Recuperer une specialite par id
    @GetMapping("/{id}")
    public ResponseEntity<Specialite> getSpecialiteById(@PathVariable("id") Long id){
        Specialite specialite = specialiteService.getSpecialiteById(id);
        return new ResponseEntity<>(specialite, HttpStatus.OK);
    }

    //Recuperer toutes les specialites
    @GetMapping
    public List<Specialite> getAllSpecialites(){
        return specialiteService.getAllSpecialite();
    }
    
   
   
   //Niveaux pour chaque filiere

  
    

    @DeleteMapping("/{id}")
    public void deleteSpecialite(@PathVariable("id") Long id){
        specialiteService.deleteSpecialite(id);
    }


  
   

}
