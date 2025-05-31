package com.example.demo.Seance;

import com.example.demo.Salle.SalleRepository;
import com.example.demo.anneeAcademique.AnneeAcademiqueRepository;
import com.example.demo.coursModule.ModuleRepository;
import com.example.demo.professeur.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class SeanceService {

    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private ProfesseurRepository professeurRepository;
    @Autowired
    private AnneeAcademiqueRepository anneeAcademiqueRepository;

    public List<Seance> getAllSeances() {
        return seanceRepository.findAll();
    }

    public Optional<Seance> getSeanceById(Long id) {
        return seanceRepository.findById(id);
    }

    public Seance createSeance(SeanceDTO dto) {
        if (seanceRepository.existsByModuleIdAndDateSeanceAndHeureDebutAndHeureFin(
                dto.getModuleId(), dto.getDateSeance(), dto.getHeureDebut(), dto.getHeureFin())) {
            throw new RuntimeException("Une séance existe déjà à cette date et heure.");
        }
        Seance seance = new Seance();
        seance.setDateSeance(dto.getDateSeance());
        seance.setHeureDebut(dto.getHeureDebut());
        seance.setHeureFin(dto.getHeureFin());
        seance.setEstEnLigne(dto.isEstEnLigne());

        // ✅ Salle seulement si le cours n’est pas en ligne
        if (!dto.isEstEnLigne() && dto.getSalleId() != null) {
            seance.setSalle(salleRepository.findById(dto.getSalleId()).orElse(null));
        } else {
            seance.setSalle(null);
        }

        seance.setModule(moduleRepository.findById(dto.getModuleId()).orElse(null));
        seance.setProfesseur(professeurRepository.findById(dto.getProfesseurId()).orElse(null));
        seance.setAnneeAcademique(anneeAcademiqueRepository.findById(dto.getAnneeAcademiqueId()).orElse(null));

        return seanceRepository.save(seance);
    }

    // Mettre à jour une séance
    public Seance updateSeance(Long id, SeanceDTO dto) {
        Optional<Seance> existingSeance = seanceRepository.findById(id);
        if (existingSeance.isPresent()) {
            Seance seance = existingSeance.get();
            seance.setDateSeance(dto.getDateSeance());
            seance.setHeureDebut(dto.getHeureDebut());
            seance.setHeureFin(dto.getHeureFin());
            seance.setEstEnLigne(dto.isEstEnLigne());
            seance.setSalle(salleRepository.findById(dto.getSalleId()).orElse(null));
            seance.setModule(moduleRepository.findById(dto.getModuleId()).orElse(null));
            seance.setProfesseur(professeurRepository.findById(dto.getProfesseurId()).orElse(null));
            seance.setAnneeAcademique(anneeAcademiqueRepository.findById(dto.getAnneeAcademiqueId()).orElse(null));
            return seanceRepository.save(seance);
        }
        return null;
    }
    



    //Supprimer une séance
    public void deleteSeance(Long id) {
        seanceRepository.deleteById(id);
    }


    //Seance par Module
    public List<Seance> getSeancesByModule(Long moduleId) {
        return seanceRepository.findByModuleId(moduleId);
    }

    //Seance par Professeur
    public List<Seance> getSeancesByProfesseur(Long professeurId) {
        return seanceRepository.findByProfesseurId(professeurId);
    }

    //Exister une séance à une date et heure donnée
    public boolean existeSeance(Long moduleId, LocalDate dateSeance, LocalTime heureDebut, LocalTime heureFin) {
        return seanceRepository.existsByModuleIdAndDateSeanceAndHeureDebutAndHeureFin(moduleId, dateSeance, heureDebut, heureFin);
    }


    //Methode pour recuperer tout le volume horaire programmé et 
      public int getTotalVolumeHoraireDeroule() {
        return seanceRepository.findAll().stream()
                .filter(s -> !s.isEstAnnulee())
                .mapToInt(Seance::getDureeEnHeures)
                .sum();
    }
}