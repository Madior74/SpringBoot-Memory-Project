package com.example.demo.etudiant.prinscription;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.ue.UeRepository;

import lombok.Data;

@Data
@Service
public class EtudiantService {

    private final UeRepository ueRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EtudiantRepository etudiantRepository;





   public Etudiant ajouterEtudiant(Etudiant etudiant) {
        // Vérification que l'étudiant n'existe pas déjà (par CNI ou INE)
        if (etudiantRepository.findByCniOrIne(etudiant.getCni(), etudiant.getIne()) != null) {
            throw new RuntimeException("Un étudiant avec ce CNI ou INE existe déjà.");
        }
         String motDePasseHache = passwordEncoder.encode(etudiant.getPassword());
    etudiant.setPassword(motDePasseHache);
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

    // Méthode pour récupérer les étudiants ayant exactement 3 documents
    public List<Etudiant> getEtudiantsAvecTroisDocuments() {
        return etudiantRepository.findEtudiantsWithThreeDocuments();
    }


    //Mettre a Jour
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
            existingEtudiant.setImagePath(etudiant.getImagePath());
            existingEtudiant.setPaysDeNaissance(etudiant.getPaysDeNaissance());
            existingEtudiant.setDateDeNaissance(etudiant.getDateDeNaissance());
            existingEtudiant.setCni(etudiant.getCni());
            existingEtudiant.setIne(etudiant.getIne());
    
            // Mise à jour des relations
            existingEtudiant.setNiveauSouhaite(etudiant.getNiveauSouhaite());
            existingEtudiant.setFiliereSouhaitee(etudiant.getFiliereSouhaitee());
            existingEtudiant.setDepartement(etudiant.getDepartement());
    
            // Sauvegarde des modifications
            return etudiantRepository.save(existingEtudiant);
        }
    
    
    

   
}
