package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.InscriptionRequestDTO;
import com.example.demo.model.AnneeAcademique;
import com.example.demo.model.DossierAdmission;
import com.example.demo.model.Etudiant;
import com.example.demo.model.Filiere;
import com.example.demo.model.Inscription;
import com.example.demo.model.Niveau;
import com.example.demo.repository.AnneeAcademiqueRepository;
import com.example.demo.repository.DossierAdmissionRepository;
import com.example.demo.repository.EtudiantRepository;
import com.example.demo.repository.FiliereRepository;
import com.example.demo.repository.InscriptionRepository;
import com.example.demo.repository.NiveauRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InscriptionService {

    private final EtudiantRepository etudiantRepository;
    private final FiliereRepository filiereRepository;
    private final NiveauRepository niveauRepository;
    private final AnneeAcademiqueRepository anneeAcademiqueRepository;
    private final DossierAdmissionRepository dossierAdmissionRepository;
    private final InscriptionRepository inscriptionRepository;

    public Inscription ajouterInscription(Inscription inscription) {

        Long etudiantId = inscription.getEtudiant().getId();
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));

        if (etudiant.getDossierAdmission() == null || !"valide".equals(etudiant.getDossierAdmission().getStatut())) {
            throw new RuntimeException("L'étudiant ne peut pas être inscrit car son dossier n'est pas complet.");
        }

        // Chargement des objets liés (sécurité contre objets partiels)
        Filiere filiere = filiereRepository.findById(inscription.getFiliere().getId())
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));
        Niveau niveau = niveauRepository.findById(inscription.getNiveau().getId())
                .orElseThrow(() -> new RuntimeException("Niveau introuvable"));
        AnneeAcademique annee = anneeAcademiqueRepository.findById(inscription.getAnneeAcademique().getId())
                .orElseThrow(() -> new RuntimeException("Année académique introuvable"));

        inscription.setEtudiant(etudiant);
        inscription.setFiliere(filiere);
        inscription.setNiveau(niveau);
        inscription.setAnneeAcademique(annee);

        if (inscription.getDateInscription() == null) {
            inscription.setDateInscription(LocalDate.now());
        }

        return inscriptionRepository.save(inscription);
    }
    //methode Simple

    public Inscription createInscription(Inscription insription){
        return  inscriptionRepository.save(insription);
    }
}
