package com.example.demo.note;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/examens")
public class ExamenController {

    @Autowired
    private NoteService noteService;

    // Endpoint pour ajouter une note d'examen
    @PostMapping
    public ResponseEntity<Examen> addExamen(@RequestBody Examen examen) {
        Examen savedExamen = noteService.addExamen(examen);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExamen);
    }

    // Endpoint pour récupérer les notes d'examen d'un étudiant dans un module
    @GetMapping("/etudiant/{etudiantId}/module/{moduleId}")
    public ResponseEntity<List<Examen>> getExamenNotes(
            @PathVariable Long etudiantId,
            @PathVariable Long moduleId) {
        List<Examen> examens = noteService.getExamenNotesByStudentAndModule(etudiantId, moduleId);
        return ResponseEntity.ok(examens);
    }
}