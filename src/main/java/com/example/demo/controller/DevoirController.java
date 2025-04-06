package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.DevoirDTO;
import com.example.demo.model.Devoir;
import com.example.demo.repository.DevoirRepository;
import com.example.demo.service.NoteService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/devoirs")
public class DevoirController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private DevoirRepository devoirRepository;

    //Recuperer tpoutes les devoirs
    @GetMapping
    public List<Devoir> getAllDevoirs(){
        return devoirRepository.findAll();
    }

    
    @PostMapping("/save")
    public Devoir addDevoir(@RequestBody Devoir devoir) {
        return noteService.addDevoir(devoir);
    }
    
    // Endpoint pour récupérer les notes de devoir d'un étudiant dans un module
    @GetMapping("/etudiant/{etudiantId}/module/{moduleId}")
    public ResponseEntity<List<Devoir>> getDevoirNotes(
            @PathVariable Long etudiantId,
            @PathVariable Long moduleId) {
        List<Devoir> devoirs = noteService.getDevoirNotesByStudentAndModule(etudiantId, moduleId);
        return ResponseEntity.ok(devoirs);
    }
}