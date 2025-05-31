package com.example.demo.note.devoir;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Seance.Seance;
import com.example.demo.etudiant.prinscription.Etudiant;

@Repository
public interface  DevoirRepository extends JpaRepository<Devoir, Long> {
    List<Devoir> findByEtudiantId(Long etudiantId);
    List<Devoir> findBySeanceId(Long seanceId);
    List<Devoir> findByEtudiantAndSeance(Etudiant etudiant, Seance seance);
    boolean existsByEtudiantAndSeanceAndDateAttribution(Etudiant etudiant, Seance seance, Date dateAttribution);

}
