package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UEException;
import com.example.demo.model.CourseModule;
import com.example.demo.model.UE;
import com.example.demo.repository.UeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UEService {

    @Autowired
    private UeRepository ueRepository;

    /**
     * Récupère toutes les UEs.
     *
     * @return La liste de toutes les UEs.
     */
    public List<UE> getAllUEs() {
        return ueRepository.findAll();
    }

    /**
     * Récupère une UE par son ID.
     *
     * @param id L'ID de l'UE.
     * @return L'UE correspondante.
     * @throws UEException Si l'UE n'est pas trouvée.
     */
    public UE getUEBy(Long id) {
        return ueRepository.findById(id)
                .orElseThrow(() -> new UEException("UE non trouvée avec l'id : " + id));
    }

    /**
     * Récupère une UE par son ID sous forme d'Optional.
     *
     * @param ueId L'ID de l'UE.
     * @return Un Optional contenant l'UE si elle existe.
     */
    public Optional<UE> getUEById(Long ueId) {
        return ueRepository.findById(ueId);
    }

    /**
     * Sauvegarde une nouvelle UE ou met à jour une existante.
     *
     * @param ue L'UE à sauvegarder.
     * @return L'UE sauvegardée.
     * @throws IllegalArgumentException Si les données sont invalides.
     */
    public UE saveUE(UE ue) {
        validateUE(ue); // Validation des données
        checkIfUEExists(ue.getNomUE(), ue.getSemestre().getId()); // Vérification d'unicité
        return ueRepository.save(ue);
    }

    /**
     * Ajoute un module à une UE existante.
     *
     * @param ueId   L'ID de l'UE cible.
     * @param module Le module à ajouter.
     * @return L'UE mise à jour.
     * @throws UEException Si l'UE n'est pas trouvée ou si le nombre maximal de modules est atteint.
     */
    public UE addModuleToUE(Long ueId, CourseModule module) {
        UE ue = ueRepository.findById(ueId)
                .orElseThrow(() -> new UEException("UE non trouvée avec l'id : " + ueId));

        if (ue.getModules().size() >= 4) {
            throw new UEException("Une UE ne peut pas avoir plus de 4 modules.");
        }

        ue.addModule(module);
        return ueRepository.save(ue);
    }

    /**
     * Supprime une UE par son ID.
     *
     * @param id L'ID de l'UE à supprimer.
     */
    public void deleteUE(Long id) {
        ueRepository.deleteById(id);
    }

    /**
     * Récupère toutes les UEs d'un semestre donné.
     *
     * @param semestreId L'ID du semestre.
     * @return La liste des UEs associées au semestre.
     */
    public List<UE> getUEBySemestre(Long semestreId) {
        return ueRepository.findBySemestreId(semestreId);
    }

    /**
     * Vérifie si une UE existe déjà pour un semestre donné.
     *
     * @param nomUE      Le nom de l'UE.
     * @param semestreId L'ID du semestre.
     * @return true si l'UE existe, sinon false.
     */
    public boolean ueExist(String nomUE, Long semestreId) {
        return ueRepository.existsByNomUEAndSemestreId(nomUE, semestreId);
    }

    /**
     * Récupère une UE par son ID.
     *
     * @param ueId L'ID de l'UE.
     * @return L'UE correspondante, ou null si non trouvée.
     */
    public UE findById(Long ueId) {
        return ueRepository.findById(ueId).orElse(null);
    }

    /**
     * Récupère toutes les UEs avec pagination.
     *
     * @param pageable Les paramètres de pagination.
     * @return Une page d'UEs.
     */
    public Page<UE> getAllUEs(Pageable pageable) {
        return ueRepository.findAll(pageable);
    }

    // /**
    //  * Récupère les UEs d'un semestre avec pagination.
    //  *
    //  * @param semestreId L'ID du semestre.
    //  * @param pageable   Les paramètres de pagination.
    //  * @return Une page d'UEs associées au semestre.
    //  */
    // public Page<UE> getUEsBySemestre(Long semestreId, Pageable pageable) {
    //     return ueRepository.findBySemestre(semestreId, pageable);
    // }

    /**
     * Validation des données d'une UE.
     *
     * @param ue L'UE à valider.
     * @throws IllegalArgumentException Si les données sont invalides.
     */
    private void validateUE(UE ue) {
        if (ue.getSemestre() == null) {
            throw new IllegalArgumentException("L'UE doit être associée à un semestre.");
        }
        if (ue.getNomUE() == null || ue.getNomUE().isEmpty()) {
            throw new IllegalArgumentException("Le nom de l'UE ne peut pas être vide.");
        }
        if (ue.getCodeUE() == null || ue.getCodeUE().isEmpty()) {
            throw new IllegalArgumentException("Le code de l'UE ne peut pas être vide.");
        }
    }

    /**
     * Vérifie si une UE existe déjà pour un semestre donné.
     *
     * @param nomUE      Le nom de l'UE.
     * @param semestreId L'ID du semestre.
     * @throws UEException Si une UE avec ce nom existe déjà dans ce semestre.
     */
    private void checkIfUEExists(String nomUE, Long semestreId) {
        if (ueExist(nomUE, semestreId)) {
            throw new UEException("Une UE avec ce nom existe déjà dans ce semestre.");
        }
    }
}