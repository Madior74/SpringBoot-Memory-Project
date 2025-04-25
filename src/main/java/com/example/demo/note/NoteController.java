package com.example.demo.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // Endpoint pour calculer la moyenne finale d'un étudiant dans un module
    @GetMapping("/moyenne/etudiant/{etudiantId}/module/{moduleId}")
    public ResponseEntity<Double> calculateFinalAverage(
            @PathVariable Long etudiantId,
            @PathVariable Long moduleId) {
        Double finalAverage = noteService.calculateFinalAverage(etudiantId, moduleId);
        return ResponseEntity.ok(finalAverage);
    }
}