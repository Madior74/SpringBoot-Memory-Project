package com.example.demo.service;

import java.time.LocalDateTime;
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





   
 public Etudiant ajouterEtudiant(Etudiant etudiant) {
        // Vérification que l'étudiant n'existe pas déjà (par CNI ou INE)
        if (etudiantRepository.findByCniOrIne(etudiant.getCni(), etudiant.getIne()) != null) {
            throw new RuntimeException("Un étudiant avec ce CNI ou INE existe déjà.");
        }
        // Définir la date d'ajout
        etudiant.setDateAjout(LocalDateTime.now());
        return etudiantRepository.save(etudiant);
    }

    // Méthode pour récupérer tous les étudiants
    public List<Etudiant> recupererTousLesEtudiants() {
        return etudiantRepository.findAll();
    }

    // Méthode pour récupérer un étudiant par son ID
    public Optional<Etudiant> recupererEtudiantParId(Long id) {
        return etudiantRepository.findById(id);
    }

    // Méthode pour supprimer un étudiant par son ID
    public void supprimerEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

     /////
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> getEtudiantById(Long id) {
        return etudiantRepository.findById(id);
    }

    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }


    //Existence de l'email
    public boolean emailExists(String email){
        return etudiantRepository.existsByEmail(email);
    }


    public List<Etudiant> getEtudiantsAvecDossierComplet() {
        return etudiantRepository.findEtudiantsAvecDossierComplet();
    }
    

    //  // Méthode pour compter les étudiants par ID de filière
    //  public long countEtudiantsByFiliereId(Long filiereId) {
    //     return etudiantRepository.countByFiliereId(filiereId);
    // }

   
}
