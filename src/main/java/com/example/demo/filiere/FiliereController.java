package com.example.demo.filiere;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.niveau.Niveau;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/filieres")
public class FiliereController {
    @Autowired
    private FiliereService filiereService;

    @Autowired
    private FiliereRepository filiereRepository;
  
    @GetMapping("/{id}/niveaux")
    public List<Niveau> getNiveauxByFiliere(@PathVariable("id") Long id) {
        return filiereService.getNiveauxByFiliere(id);
    }

    @GetMapping
    public List<Filiere> getAllFilieres() {
        return filiereService.getAllFilieres();
    }

   
@GetMapping("/{id}")
public Filiere getFiliereById(@PathVariable("id") Long id) {
    return filiereService.getFiliereById(id);
}

    @PostMapping
    public Filiere createFiliere(@RequestBody Filiere filiere) {
        return filiereService.saveFiliere(filiere);
    }

  
@DeleteMapping("/{id}")
public void deleteFiliere(@PathVariable("id") Long id) {
    filiereService.deleteFiliere(id);
}


//Auto
    @PostMapping("/auto")
    public ResponseEntity<Filiere> createFiliere(@RequestBody FiliereRequest request) {
        Filiere filiere = filiereService.createFiliereAvecStructure(request);
        return ResponseEntity.ok(filiere);
    }


//nombre d'etudiant

    @GetMapping("/{id}/etudiants/count")
    public ResponseEntity<Map<String, Integer>> getEtudiantsCount(@PathVariable Long id) {
        int count = filiereService.getEtudiantsCountByFiliereId(id);
        Map<String, Integer> response = new HashMap<>();
        response.put("count", count);
        return ResponseEntity.ok(response);
    }

    //Mettre a jour
    @PutMapping("/{id}")
public Filiere updateFiliere(@PathVariable Long id, @RequestBody Filiere updatedFiliere) {
    Filiere filiere = filiereRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Filière non trouvée"));

    filiere.setNomFiliere(updatedFiliere.getNomFiliere());
    filiere.setDescription(updatedFiliere.getDescription());

    return filiereRepository.save(filiere);
}


    // //VER
    //   @GetMapping
    // public List<Filiere> getFilieresByNom(@RequestParam(required = false) String nomFiliere) {
    //     if (nomFiliere != null && !nomFiliere.isEmpty()) {
    //         return filiereService.findByNomFiliereIgnoreCase(nomFiliere);
    //     } else {
    //         return filiereService.getAllFilieres();
    //     }
    // }

}