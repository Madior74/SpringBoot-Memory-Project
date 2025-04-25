package com.example.demo.etudiant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long>{


    // //Recuperer tous les etudiants d'une session
    // List<Etudiant> findByanneeAcademiqueId(@Param("anneeAcademiqueId") Long anneeAcademiqueId);

 
     Optional<Etudiant> findByCni(String cni);
    Optional<Etudiant> findByIne(String ine);
  
      Optional<Etudiant> findByEmail(String email);


      boolean existsByEmail(String email );

  // Méthode pour rechercher un étudiant par son CNI ou INE
  Etudiant findByCniOrIne(String cni, String ine);
//Recuperer les dossiers des  valides des Etudiants
  @Query("SELECT e FROM Etudiant e WHERE e.dossierAdmission.statut = 'COMPLET'")
List<Etudiant> findEtudiantsAvecDossierComplet();


    
}
