package com.example.demo.professeur;



import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.Specialite.SpecialiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Specialite.Specialite;

@Service
public class ProfesseurService {

    @Autowired
    private ProfesseurRepository professeurRepository;

    @Autowired
    private SpecialiteRepository specialiteRepository;

    // Convertir Professeur en ProfesseurDTO
    private ProfesseurDTO convertToDTO(Professeur professeur) {
        return new ProfesseurDTO(professeur);
    }

    // Récupérer tous les professeurs
    public List<ProfesseurDTO> getAllProfesseurs() {
        return professeurRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Récupérer par ID
    public ProfesseurDTO getProfesseurById(Long id) {
        return professeurRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Professeur non trouvé avec l'id " + id));
    }

    // Créer un nouveau professeur
    public ProfesseurDTO createProfesseur(Professeur professeur) {
        return convertToDTO(professeurRepository.save(professeur));
    }

    // Mettre à jour un professeur
    public ProfesseurDTO updateProfesseur(Long id, Professeur professeurDetails) {
        Professeur professeur = professeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professeur non trouvé"));

        // Mise à jour des champs
        professeur.setNom(professeurDetails.getNom());
        professeur.setPrenom(professeurDetails.getPrenom());
        professeur.setAdresse(professeurDetails.getAdresse());
        professeur.setPaysDeNaissance(professeurDetails.getPaysDeNaissance());
        professeur.setDateDeNaissance(professeurDetails.getDateDeNaissance());
        professeur.setImagePath(professeurDetails.getImagePath());
        professeur.setCni(professeurDetails.getCni());
        professeur.setIne(professeurDetails.getIne());
        professeur.setTelephone(professeurDetails.getTelephone());
        professeur.setSexe(professeurDetails.getSexe());
        professeur.setEmail(professeurDetails.getEmail());
        professeur.setRole(professeurDetails.getRole());
        professeur.setGrade(professeurDetails.getGrade());
        professeur.setEstPermanent(professeurDetails.isEstPermanent());
        professeur.setSpecialites(professeurDetails.getSpecialites());
        professeur.setRegion(professeurDetails.getRegion());
        professeur.setDepartement(professeurDetails.getDepartement());

        return convertToDTO(professeurRepository.save(professeur));
    }

    // Supprimer un professeur
    public void deleteProfesseur(Long id) {
        professeurRepository.deleteById(id);
    }



    //Specialité par professeur
    public List<Specialite> getSpecialitesByProfesseurId(Long id) {
        Professeur professeur = professeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professeur non trouvé avec l'id " + id));
        return professeur.getSpecialites();
}   


    // Ajouter une spécialité à un professeur
    public void addSpecialitesToProfesseur(Long profId, List<Long> specialiteIds) {
        Professeur professeur = professeurRepository.findById(profId)
                .orElseThrow(() -> new RuntimeException("Professeur non trouvé"));

        List<Specialite> specialites = specialiteRepository.findAllById(specialiteIds);

        professeur.setSpecialites(specialites); // ou professeur.getSpecialites().addAll(...)
        professeurRepository.save(professeur);
    }
    // Supprimer une spécialité d'un professeur
    public void removeSpecialiteFromProfesseur(Long id, Long specialiteId) {
        Professeur professeur = professeurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professeur non trouvé avec l'id " + id));
        Specialite specialite = professeur.getSpecialites().stream()
                .filter(s -> s.getId().equals(specialiteId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Spécialité non trouvée avec l'id " + specialiteId));
        professeur.getSpecialites().remove(specialite);
        professeurRepository.save(professeur);
    }
    // Obtenir le nombre de professeurs
    public long countProfesseurs() {
        return professeurRepository.count();
    }
}