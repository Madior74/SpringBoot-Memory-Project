package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.MaquetteSemestreDTO;
import com.example.demo.model.CourseModule;
import com.example.demo.model.Semestre;
import com.example.demo.model.UE;
import com.example.demo.repository.SemestreRepository;
import com.example.demo.service.MaquetteService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maquettes")
public class MaquetteController {

    @Autowired
    private MaquetteService maquetteService;

    /**
     * Endpoint pour générer la maquette d'un semestre spécifique.
     *
     * @param semestreId L'ID du semestre pour lequel générer la maquette.
     * @return La maquette du semestre au format JSON.
     */
    @GetMapping("/semestre/{semestreId}")
    public ResponseEntity<MaquetteSemestreDTO> getMaquetteSemestre(@PathVariable Long semestreId) {
        try {
            // Appeler le service pour générer la maquette
            MaquetteSemestreDTO maquette = maquetteService.generateMaquetteSemestre(semestreId);

            // Retourner la maquette avec un statut HTTP 200 (OK)
            return ResponseEntity.ok(maquette);
        } catch (Exception e) {
            // Gérer les erreurs et retourner un statut HTTP 404 (Not Found) ou 500 (Internal Server Error)
            return ResponseEntity.status(500).body(null);
        }
    }
}