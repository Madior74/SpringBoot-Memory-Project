package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Filiere;
import com.example.demo.model.Niveau;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Long> {
    Optional<Niveau> findByNomNiveauAndFiliere(String nomNiveau, Filiere filiere);
    List<Niveau> findByFiliereId(Long filiereId);

    //Verification
    boolean existsByNomNiveauAndFiliereId(String nomNiveau, Long filiereId);  
    boolean existsByNomNiveauAndFiliere(String nomNiveau, Filiere filiere);


    
}

