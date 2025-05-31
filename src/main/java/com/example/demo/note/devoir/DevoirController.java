package com.example.demo.note.devoir;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/devoirs")
public class DevoirController {

  
    @Autowired
    private DevoirRepository devoirRepository;

    //Recuperer tpoutes les devoirs
    @GetMapping
    public List<Devoir> getAllDevoirs(){
        return devoirRepository.findAll();
    }

    
    @PostMapping("/save")
    public Devoir addDevoir(@RequestBody Devoir devoir) {
        return devoirRepository.save(devoir);
    }
    
    // Endpoint pour récupérer les notes de devoir d'un étudiant dans un module
    // @GetMapping("/etudiant/{etudiantId}/module/{moduleId}")
    // public ResponseEntity<List<Devoir>> getDevoirNotes(
    //         @PathVariable Long etudiantId,
    //         @PathVariable Long moduleId) {
    //     List<Devoir> devoirs = devoirRepository.getDevoirNotesByStudentAndModule(etudiantId, moduleId);
    //     return ResponseEntity.ok(devoirs);
    // }

    //Supprimer la note de devoir 
    @DeleteMapping("/{id}")
    public void deleteDevoir(@PathVariable Long id){
        devoirRepository.deleteById(id);



    }



}