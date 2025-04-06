package com.example.demo.controller;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Semestre;
import com.example.demo.model.UE;
import com.example.demo.repository.UeRepository;

import com.example.demo.service.SemestreService;
import com.example.demo.service.UEService;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/ues")
public class UEController {

    @Autowired
    private UeRepository ueRepository;

    @Autowired
    private SemestreService semestreService;


    @Autowired
    private UEService ueService;

    // Récupérer toutes les UEs
    @GetMapping
    public ResponseEntity<List<UE>> getAllUEs() {
        List<UE> ues = ueRepository.findAll();
        return new ResponseEntity<>(ues, HttpStatus.OK);
    }

    // Récupérer une UE par son ID
    @GetMapping("/{id}")
    public ResponseEntity<UE> getUEById(@PathVariable("id") Long id) {
        Optional<UE> ueData = ueRepository.findById(id);

        if (ueData.isPresent()) {
            return new ResponseEntity<>(ueData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // //UE by Semestre
    @GetMapping("/semestre/{semestreId}")
    public List<UE> getUEsBySemestre(@PathVariable Long semestreId) {
        return ueService.getUEBySemestre(semestreId);
    }





//--------------------------------------------------------------------------------

      //Ajouter une UE a un Semestre
    @PostMapping("/{semestreId}/ue")
    public ResponseEntity<?> addUEToSemestre(@PathVariable Long semestreId, @RequestBody Map<String, Object> body) {
    // Affiche les données reçues pour le débogage
    System.out.println("Données reçues : " + body);

    // Extraction des données du corps de la requête

    Object semestreObj = body.get("semestre");

    // Vérifier si les éléments sont présents et valides
   
    if (semestreObj == null || ((Map) semestreObj).get("id") == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Semestre manquant ou invalide");
    }

    // Récupérer les objets Semestre, Niveau et Filiere
    Semestre semestre = semestreService.getSemestreById(semestreId);
    
    // Créer une nouvelle UE
    UE ue = new UE();
    ue.setSemestre(semestre);
  
    // Assigner les autres propriétés
    ue.setNomUE((String) body.get("nomUE"));
    ue.setCodeUE((String) body.get("codeUE"));

    // Conversion de la dateAjout en LocalDateTime
    String dateAjoutStr = (String) body.get("dateAjout");
    if (dateAjoutStr != null) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime dateAjout = LocalDateTime.parse(dateAjoutStr, formatter);
        ue.setDateAjout(dateAjout);
    }

    // Enregistrer l'UE
    UE savedUe = ueService.saveUE(ue);

    // Réponse avec les données de l'UE ajoutée
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUe);
}





    ///Existence de l'UE
    @GetMapping("/exists")
   public ResponseEntity<Boolean> checkUEExist(@RequestParam String nomUE,@RequestParam Long semestreId){
        boolean exists=ueService.ueExist(nomUE, semestreId);

        return ResponseEntity.ok(exists);
   }



    @DeleteMapping("/{id}")
    public void deleteUE(@PathVariable Long id) {
        ueService.deleteUE(id);
    }
//---------------------------------------------------

   // Mettre à jour une UE existante
   @PutMapping("/{ueId}")
public ResponseEntity<?> updateUE(@PathVariable Long ueId, @RequestBody Map<String, Object> body) {
    // Affiche les données reçues pour le débogage
    System.out.println("Données reçues pour mise à jour : " + body);

    // Vérifier si l'UE existe
    Optional<UE> existingUEOpt = ueService.getUEById(ueId);
    if (!existingUEOpt.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UE non trouvée");
    }

    // Récupérer l'UE existante
    UE existingUE = existingUEOpt.get();

    // Mettre à jour les propriétés de l'UE
    existingUE.setNomUE((String) body.get("nomUE"));
    existingUE.setCodeUE((String) body.get("codeUE"));

    // Récupérer les objets Niveau, Filière et Semestre si nécessaire
    if (body.get("semestre") != null) {
        Object semestreObjRaw = body.get("semestre");
        if (!(semestreObjRaw instanceof Map)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid semestre object");
        }
        if (semestreObjRaw instanceof Map<?, ?>) {
            Map<String, Object> semestreObj = (Map<String, Object>) semestreObjRaw;
            if (semestreObj.get("id") != null) {
                Semestre semestre = semestreService.getSemestreById(Long.valueOf(semestreObj.get("id").toString()));
                existingUE.setSemestre(semestre);
            }
        }
    }

    // Conversion de la dateAjout en LocalDateTime (si nécessaire)
    String dateAjoutStr = (String) body.get("dateAjout");
    if (dateAjoutStr != null) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime dateAjout = LocalDateTime.parse(dateAjoutStr, formatter);
        existingUE.setDateAjout(dateAjout);
    }

    // Enregistrer l'UE mise à jour
    UE updatedUE = ueService.saveUE(existingUE);

    // Réponse avec les données de l'UE mise à jour
    return ResponseEntity.ok(updatedUE);
}


    
}


