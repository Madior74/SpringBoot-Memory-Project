package com.example.demo.etudiant.admission;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.etudiant.prinscription.Etudiant;
import com.example.demo.etudiant.inscription.InscriptionRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/dossiers")
public class AdmissionController {

    @Autowired
    private DossierAdmissionService dossierAdmissionService;

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;


    //Recuperer tous les dossiers
    // @GetMapping
    // public List<DossierAdmission> getAllDossierAdmission(){
    //     return dossierAdmissionService.getAllDossierAdmission();
    // }

    @GetMapping()
public List<Admission> getDossiersNonInscrits() {
    return admissionRepository.findAll().stream()
        .filter(dossier -> {
            Etudiant etudiant = dossier.getEtudiant();
            return etudiant != null &&
                   !inscriptionRepository.existsByEtudiantId(etudiant.getId());
        })
        .collect(Collectors.toList());
}


    //Dossier par Id
    @GetMapping("/{id}")
    public Admission getDossierAdmissionById(@PathVariable Long id){
        return dossierAdmissionService.getDossierAdmissionById(id);
    }
    


    //Creer un nouveau dossier
    @PostMapping("/save")
    public Admission createDossierAdmission(@RequestBody Admission dossierAdmission) {
        Long etudiantId = dossierAdmission.getEtudiant().getId();
        return dossierAdmissionService.createDossier(dossierAdmission, etudiantId);
    }
    
    

//Update
@PutMapping("/update/{id}")
public ResponseEntity<Admission> updateDossier(@PathVariable Long id,
                                               @RequestBody Admission dossierDetails) {
    Admission updated = dossierAdmissionService.updateDossier(id, dossierDetails);
    return ResponseEntity.ok(updated);
}

    //Existence
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkDossierExists(@RequestParam Long etudiantId){
        boolean exists=dossierAdmissionService.existsByEtudiant(etudiantId);
        return  ResponseEntity.ok(exists);
    }




    //supprime d’abord la référence dans l'étudiant, ensuite  supprime le dossier 
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteDossier(@PathVariable Long id){
        Optional<Admission> dossierOptional = admissionRepository.findById(id);
    
        if (!dossierOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dossier non trouvé");
        }
    
        Admission dossier = dossierOptional.get();
    
        if (dossier.getEtudiant() != null) {
            dossier.getEtudiant().setDossierAdmission(null);
        }
    
        admissionRepository.delete(dossier);
    
        return ResponseEntity.ok("Dossier supprimé");
    }
    
}
   
