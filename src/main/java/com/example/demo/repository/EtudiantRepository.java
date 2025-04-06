package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Etudiant;
import com.example.demo.model.Filiere;
import com.example.demo.model.Niveau;
@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long>{


    //Recuperer tous les etudiants d'une session
    List<Etudiant> findBySessionId(@Param("sessionId") Long sessionId);

 
     Optional<Etudiant> findByCni(String cni);
    Optional<Etudiant> findByIne(String ine);
      List<Etudiant> findByFiliere(Filiere filiere);
      List<Etudiant> findByNiveau(Niveau niveau);
      Optional<Etudiant> findByEmail(String email);


      boolean existsByEmail(String email );

   //Pour  tous les etudiants inscrit dans une Filiere
   List<Etudiant> findByFiliereId(Long filiereId);

   //Pour tous les etudiants inscrit dans un Niveau
   List<Etudiant> findByNiveauId(Long NiveauId);

  
    // Compter par ID de filière
    long countByFiliereId(Long filiereId);

    // //Recuperer les eudiants par Niveau
    // @Query("SELECT e FROM Etudiant e WHERE e.niveau.id=:niveauId")
    // List<Etudiant> findByNiveauId(@Param("niveauId") Long niveauId);

    // //Recupere un etudiant specifique
    // // Optional<Etudiant> findByNomEtudiant(String nomEtudiant);


    
}
