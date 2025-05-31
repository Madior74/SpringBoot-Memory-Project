package com.example.demo.etudiant.inscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InscriptionRepository  extends JpaRepository<Inscription,Long>{
//Verification si l'etudiant est deja inscrit dans le Systeme
boolean existsByEtudiantId(Long etudiantId);

 // Vérifie si l'étudiant a déjà une inscription identique
 boolean existsByEtudiantIdAndFiliereIdAndAnneeAcademiqueId(
    Long etudiantId, Long filiereId, Long anneeAcademiqueId);




 @Query("SELECT COUNT(i) FROM Inscription i WHERE i.filiere.id = :filiereId")
 int countEtudiantsByFiliereId(@Param("filiereId") Long filiereId);



 //Par niveau
 @Query("SELECT i FROM Inscription i WHERE i.niveau.id = :niveauId")
List<Inscription> findByNiveauId(@Param("niveauId") Long niveauId);
}
