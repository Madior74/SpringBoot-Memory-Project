package com.example.demo.filiere;
import java.util.List;

import com.example.demo.etudiant.inscription.InscriptionRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.anneeAcademique.AnneeAcademiqueRepository;
import com.example.demo.niveau.Niveau;
import com.example.demo.niveau.NiveauRepository;
import com.example.demo.semestre.Semestre;
import com.example.demo.semestre.SemestreRepository;

import lombok.Data;





@Data
@Service
public class FiliereService {
    @Autowired
    private FiliereRepository filiereRepository;

    @Autowired
    private AnneeAcademiqueRepository anneeAcademiqueRepository;

    @Autowired
    private NiveauRepository niveauRepository;

    @Autowired
    private SemestreRepository semestreRepository;

    @Autowired
    private InscriptionRepository inscriptionRepository;


    //

    public List<Filiere> getAllFilieres() {
        return filiereRepository.findAll();
    }

    public Filiere getFiliereById(Long id) {
        return filiereRepository.findById(id).orElseThrow(() -> new RuntimeException("Filière non trouvée"));
    }

    public Filiere saveFiliere(Filiere filiere) {
        return filiereRepository.save(filiere);
    }

    public void deleteFiliere(final Long id) {
        filiereRepository.deleteById(id);
    }

    public List<Niveau> getNiveauxByFiliere(Long filiereId) {
        Filiere filiere = filiereRepository.findById(filiereId)
                .orElseThrow(() -> new RuntimeException("Filière non trouvée"));
        return filiere.getNiveaux();
    }

     //
     public Filiere findById(Long id) {
        return filiereRepository.findById(id).orElse(null);
    }

//Auto 

public Filiere createFiliereAvecStructure(String nomFiliere) {
    Filiere filiere = new Filiere();
    filiere.setNomFiliere(nomFiliere);
    filiere = filiereRepository.save(filiere);

    // Définition des niveaux et semestres
    List<Pair<String, List<String>>> structure = List.of(
            Pair.of("Licence 1", List.of("Semestre 1", "Semestre 2")),
            Pair.of("Licence 2", List.of("Semestre 3", "Semestre 4")),
            Pair.of("Licence 3", List.of("Semestre 5", "Semestre 6"))
    );


    for (Pair<String, List<String>> entry : structure) {
        Niveau niveau = new Niveau();
        niveau.setNomNiveau(entry.getLeft());
        niveau.setFiliere(filiere);
        niveau = niveauRepository.save(niveau);

        for (String nomSemestre : entry.getRight()) {
            Semestre semestre = new Semestre();
            semestre.setNomSemestre(nomSemestre);
            semestre.setNiveau(niveau);
            semestreRepository.save(semestre);
        }
    }


    return filiere;
}


    //Nombre detudiant par filiere
    public int getEtudiantsCountByFiliereId(Long filiereId) {
        return inscriptionRepository.countEtudiantsByFiliereId(filiereId);
    }




}
        
