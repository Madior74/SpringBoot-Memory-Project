package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

import com.example.demo.model.Devoir;
import com.example.demo.service.DevoirService;

@RestController
@RequestMapping("/devoirs")
public class DevoirController {

  
    @Autowired
    private DevoirService devoirService;

    
    @GetMapping
    public List<Devoir> getAllNotes() {
        return devoirService.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devoir> getDevoirById(@PathVariable Long id) {
        Optional<Devoir> note = devoirService.getDevoirById(id);
        return note.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/etudiant/{etudiantId}")
    public List<Devoir> getDevoirByEtudiant(@PathVariable Long etudiantId) {
        return devoirService.getDevoirByEtudiant(etudiantId);
    }

    @GetMapping("/module/{moduleId}")
    public List<Devoir> getDevoirByModule(@PathVariable Long moduleId) {
        return devoirService.getDevoirByModule(moduleId);
    }

    @PostMapping("/save")
public Devoir createDevoir(@RequestBody Devoir devoir) {
   return devoirService.saveNote(devoir);
   
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        devoirService.deleteDevoir(id);
        return ResponseEntity.noContent().build();
    }
}