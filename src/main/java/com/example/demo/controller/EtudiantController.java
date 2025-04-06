package com.example.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Departement;
import com.example.demo.model.Etudiant;
import com.example.demo.model.Filiere;
import com.example.demo.model.Niveau;
import com.example.demo.model.Region;
import com.example.demo.model.Session;
import com.example.demo.service.EtudiantService;
import com.example.demo.service.FiliereService;
import com.example.demo.service.NiveauService;
import com.example.demo.service.SessionService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/etudiants")
public class EtudiantController {
    

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private FiliereService filiereService;

    @Autowired
    private NiveauService niveauService;


    

     @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        return etudiantService.getEtudiantById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Enregistre un nouveau Etudiant
   
@PostMapping("/save")
public ResponseEntity<?> ajouterEtudiant(@RequestBody Map<String, Object> payload) {
    System.out.println("Données reçues : " + payload);
    // Validate required fields
    Map<String, String> errors = new HashMap<>();
    
    // Basic fields validation
    if (!payload.containsKey("prenom")) errors.put("prenom", "Field is required");
    if (!payload.containsKey("nom")) errors.put("nom", "Field is required");
    if (!payload.containsKey("cni")) errors.put("cni", "Field is required");
    if (!payload.containsKey("ine")) errors.put("ine", "Field is required");
    if (!payload.containsKey("dateNaissance")) errors.put("dateNaissance", "Field is required");
    
    // Relationship validation
    if (!payload.containsKey("region")) errors.put("region", "region is required");
    if (!payload.containsKey("departement")) errors.put("departement", "Field is required");
    if (!payload.containsKey("filiere")) errors.put("filiere", "Field is required");
    if (!payload.containsKey("niveau")) errors.put("niveau", "Field is required");
    if (!payload.containsKey("session")) errors.put("session", "Field is required");
    
    if (!errors.isEmpty()) {
        return ResponseEntity.badRequest()
            .body(Map.of(
                "status", "error",
                "message", "Validation failed",
                "errors", errors
            ));
    }

    // Create and populate student
    Etudiant etudiant = new Etudiant();
    try {
        // Set basic fields
        etudiant.setPrenom((String) payload.get("prenom"));
        etudiant.setNom((String) payload.get("nom"));
        etudiant.setAdresse((String) payload.get("adresse"));
        etudiant.setTelephone((String) payload.get("telephone"));
        etudiant.setSexe((String) payload.get("sexe"));
        etudiant.setEmail((String) payload.get("email"));
        etudiant.setPassword((String) payload.get("password"));
        etudiant.setImagePath((String) payload.get("imagePath"));
        etudiant.setPaysDeNaissance((String) payload.get("paysDeNaissance"));
        etudiant.setCni((String) payload.get("cni"));
        etudiant.setIne((String) payload.get("ine"));
        etudiant.setRole((String) payload.get("role"));

        // Handle date
        String dateNaissanceStr = (String) payload.get("dateNaissance");
        etudiant.setDateDeNaissance(LocalDate.parse(dateNaissanceStr.substring(0, 10)));

        // Gerer les relation
        Map<String,Object> regionMap=(Map<String,Object>) payload.get("region");
        Region  region=new Region();
        region.setId(((Number) regionMap.get("id")).longValue());
        etudiant.setRegion(region);



        Map<String, Object> departementMap = (Map<String, Object>) payload.get("departement");
        Departement departement = new Departement();
        departement.setId(((Number) departementMap.get("id")).longValue());
        etudiant.setDepartement(departement);

        Map<String, Object> filiereMap = (Map<String, Object>) payload.get("filiere");
        Filiere filiere = new Filiere();
        filiere.setId(((Number) filiereMap.get("id")).longValue());
        etudiant.setFiliere(filiere);

        Map<String, Object> niveauMap = (Map<String, Object>) payload.get("niveau");
        Niveau niveau = new Niveau();
        niveau.setId(((Number) niveauMap.get("id")).longValue());
        etudiant.setNiveau(niveau);

        Map<String, Object> sessionMap = (Map<String, Object>) payload.get("session");
        Session session = new Session();
        session.setId(((Number) sessionMap.get("id")).longValue());
        etudiant.setSession(session);

        // Save the student
        Etudiant nouvelEtudiant = etudiantService.ajouterEtudiant(etudiant);
        return ResponseEntity.ok(nouvelEtudiant);

    } catch (DateTimeParseException e) {
        return ResponseEntity.badRequest()
            .body(Map.of("status", "error", "message", "Invalid date format"));
    } catch (Exception e) {
        return ResponseEntity.internalServerError()
            .body(Map.of("status", "error", "message", e.getMessage()));
    }
}

    @DeleteMapping("/{id}")
    public void deleteEtudiant(@PathVariable Long id){
        etudiantService.deleteEtudiant(id);
    }


    //Verification de l'existence 
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkEtudiantExists(@RequestParam String email){
        boolean exists=etudiantService.emailExists(email);
        return ResponseEntity.ok(exists);
    }
    


//Mettre a jour un etudiant
@PutMapping("/{id}")
public ResponseEntity<?> updateEtudiant(@PathVariable("id") Long etudiantId, @RequestBody Map<String, Object> body) {

    // Afficher les données reçues
    System.out.println("Données reçues : " + body);

    // Vérification de l'existence de l'étudiant
    Optional<Etudiant> existEtudiantOpt = etudiantService.getEtudiantById(etudiantId);
    if (!existEtudiantOpt.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Étudiant non trouvé");
    }

    // Récupérer l'étudiant existant
    Etudiant exisEtudiant = existEtudiantOpt.get();

    // Mettre à jour les propriétés de l'étudiant
    if (body.containsKey("nom")) {
        exisEtudiant.setNom((String) body.get("nom"));
    }
    if (body.containsKey("prenom")) {
        exisEtudiant.setPrenom((String) body.get("prenom"));
    }
    if (body.containsKey("adresse")) {
        exisEtudiant.setAdresse((String) body.get("adresse"));
    }
    if (body.containsKey("paysDeNaissance")) {
        exisEtudiant.setPaysDeNaissance((String) body.get("paysDeNaissance"));
    }
    if (body.containsKey("cni")) {
        exisEtudiant.setCni((String) body.get("cni"));
    }
    if (body.containsKey("ine")) {
        exisEtudiant.setIne((String) body.get("ine"));
    }
    if (body.containsKey("telephone")) {
        exisEtudiant.setTelephone((String) body.get("telephone"));
    }
    if (body.containsKey("sexe")) {
        exisEtudiant.setSexe((String) body.get("sexe"));
    }
    if (body.containsKey("email")) {
        exisEtudiant.setEmail((String) body.get("email"));
    }
    if (body.containsKey("password")) {
        exisEtudiant.setPassword((String) body.get("password"));
    }

    // Gestion de la date de naissance
    if (body.containsKey("dateDeNaissance")) {
        String dateNaissanceStr = (String) body.get("dateDeNaissance");
        try {
            exisEtudiant.setDateDeNaissance(LocalDate.parse(dateNaissanceStr));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Format de date incorrect. Utilisez le format 'yyyy-MM-dd'.");
        }
    }

    // Gestion des relations (Filiere, Niveau, Session)
    if (body.containsKey("filiere") && body.get("filiere") instanceof Map) {
        Map<String, Object> filiereMap = (Map<String, Object>) body.get("filiere");
        Long filiereId = ((Number) filiereMap.get("id")).longValue();
        Filiere filiere = filiereService.getFiliereById(filiereId);
        if (filiere == null) {
            return ResponseEntity.badRequest().body("Filière non trouvée avec l'ID : " + filiereId);
        }
        exisEtudiant.setFiliere(filiere);
    }

    if (body.containsKey("niveau") && body.get("niveau") instanceof Map) {
        Map<String, Object> niveauMap = (Map<String, Object>) body.get("niveau");
        Long niveauId = ((Number) niveauMap.get("id")).longValue();
        Niveau niveau = niveauService.getNiveauById(niveauId);
        if (niveau == null) {
            return ResponseEntity.badRequest().body("Niveau non trouvé avec l'ID : " + niveauId);
        }
        exisEtudiant.setNiveau(niveau);
    }

    if (body.containsKey("session") && body.get("session") instanceof Map) {
        Map<String, Object> sessionMap = (Map<String, Object>) body.get("session");
        Long sessionId = ((Number) sessionMap.get("id")).longValue();
        Optional<Session> sessionOptional = sessionService.getSession(sessionId);
        if (!sessionOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Session non trouvée avec l'ID : " + sessionId);
        }
        exisEtudiant.setSession(sessionOptional.get());
    }

    // Enregistrement des modifications
    try {
        Etudiant updatedEtudiant = etudiantService.updateEtudiant(exisEtudiant);
        return ResponseEntity.ok(updatedEtudiant); // Retourne l'étudiant mis à jour
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour de l'étudiant : " + e.getMessage());
    }
}






///Les Filtres
   // Récupérer les étudiants par ID de filière
   @GetMapping("/filiere/{filiereId}")
   public ResponseEntity<List<Etudiant>> getEtudiantsByFiliereId(@PathVariable Long filiereId) {
       List<Etudiant> etudiants = etudiantService.getEtudiantsByFiliereId(filiereId);
       if (etudiants.isEmpty()) {
           return ResponseEntity.noContent().build(); // 204 No Content si aucun étudiant n'est trouvé
       }
       return ResponseEntity.ok(etudiants); // 200 OK avec la liste des étudiants
   }



   @GetMapping("/filiere/{filiereId}/count")
   public ResponseEntity<Long> getEtudiantsCountByFiliereId(@PathVariable Long filiereId) {
       long count = etudiantService.countEtudiantsByFiliereId(filiereId);
       return ResponseEntity.ok(count);
   }



   // Récupérer les étudiants par ID de niveau
   @GetMapping("/niveau/{niveauId}")
   public ResponseEntity<List<Etudiant>> getEtudiantsByNiveauId(@PathVariable Long niveauId) {
       List<Etudiant> etudiants = etudiantService.getEtudiantaByNiveauId(niveauId);
       if (etudiants.isEmpty()) {
           return ResponseEntity.noContent().build(); // 204 No Content si aucun étudiant n'est trouvé
       }
       return ResponseEntity.ok(etudiants); // 200 OK avec la liste des étudiants
   }

}
