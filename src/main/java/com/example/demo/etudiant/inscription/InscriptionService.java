package com.example.demo.etudiant.inscription;

import org.springframework.stereotype.Service;

import com.example.demo.anneeAcademique.AnneeAcademique;
import com.example.demo.anneeAcademique.AnneeAcademiqueRepository;
import com.example.demo.etudiant.prinscription.Etudiant;
import com.example.demo.etudiant.prinscription.EtudiantRepository;
import com.example.demo.etudiant.admission.DossierAdmissionRepository;
import com.example.demo.filiere.Filiere;
import com.example.demo.filiere.FiliereRepository;
import com.example.demo.niveau.Niveau;
import com.example.demo.niveau.NiveauRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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
   


    //Existence de l'inscription
    public boolean checkIfInscriptionExists(Long etudiantId, Long filiereId, Long anneeAcademiqueId) {
        return inscriptionRepository.existsByEtudiantIdAndFiliereIdAndAnneeAcademiqueId(etudiantId, filiereId, anneeAcademiqueId);
    }




}
