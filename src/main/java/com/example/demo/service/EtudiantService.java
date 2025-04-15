package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Etudiant;
import com.example.demo.repository.EtudiantRepository;
import com.example.demo.repository.UeRepository;
import lombok.Data;

@Data
@Service
public class EtudiantService {

    private final UeRepository ueRepository;




    @Autowired
    private EtudiantRepository etudiantRepository;



   
    //Get Etudiant by Filiere
    public List<Etudiant> getEtudiantsByFiliereId(Long filiereId){
        return etudiantRepository.findByFiliereId(filiereId);
    }

   
    // //Get Etudiant by Niveau

    public List<Etudiant> getEtudiantaByNiveauId(Long niveauId){
        return etudiantRepository.findByNiveauId(niveauId);
    }


    //get etudiant by session
    public List<Etudiant> getEtudiantsBySession(Long sessionId){
        return etudiantRepository.findByanneeAcademiqueId(sessionId);
    }

   

    //  //Rechercher un Etudiant
    //   public Etudiant findById( Long etudiantId){
    //     return etudiantRepository.findById(etudiantId).orElse(null);
    //   }

    //Mise a jour d'un etudiant
    public Etudiant updateEtudiant(Etudiant etudiant) {
        // Vérification si l'étudiant existe dans la base de données
        Optional<Etudiant> existingEtudiantOptional = etudiantRepository.findById(etudiant.getId());
        if (!existingEtudiantOptional.isPresent()) {
            throw new RuntimeException("Étudiant non trouvé avec l'ID : " + etudiant.getId());
        }

        // Récupération de l'étudiant existant
        Etudiant existingEtudiant = existingEtudiantOptional.get();

        // Mise à jour des champs modifiables
        existingEtudiant.setPrenom(etudiant.getPrenom());
        existingEtudiant.setNom(etudiant.getNom());
        existingEtudiant.setAdresse(etudiant.getAdresse());
        existingEtudiant.setTelephone(etudiant.getTelephone());
        existingEtudiant.setSexe(etudiant.getSexe());
        existingEtudiant.setEmail(etudiant.getEmail());
        existingEtudiant.setPassword(etudiant.getPassword());
        existingEtudiant.setImagePath(etudiant.getImagePath());
        existingEtudiant.setPaysDeNaissance(etudiant.getPaysDeNaissance());
        existingEtudiant.setDateDeNaissance(etudiant.getDateDeNaissance());
        existingEtudiant.setCni(etudiant.getCni());
        existingEtudiant.setIne(etudiant.getIne());
        existingEtudiant.setRole(etudiant.getRole());
        existingEtudiant.setRegion(etudiant.getRegion());

        // Mise à jour des relations
        existingEtudiant.setNiveau(etudiant.getNiveau());
        existingEtudiant.setAnneeAcademique(etudiant.getAnneeAcademique());
        existingEtudiant.setFiliere(etudiant.getFiliere());
        existingEtudiant.setDepartement(etudiant.getDepartement());

        // Sauvegarde des modifications
        return etudiantRepository.save(existingEtudiant);
    }



     /////
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> getEtudiantById(Long id) {
        return etudiantRepository.findById(id);
    }

    public Etudiant ajouterEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }


    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }


    //Existence de l'email
    public boolean emailExists(String email){
        return etudiantRepository.existsByEmail(email);
    }




     // Méthode pour compter les étudiants par ID de filière
     public long countEtudiantsByFiliereId(Long filiereId) {
        return etudiantRepository.countByFiliereId(filiereId);
    }

   
}
