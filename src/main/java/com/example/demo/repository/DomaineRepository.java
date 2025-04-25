package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.filiere.Filiere;
import com.example.demo.model.Domaine;


@Repository
public interface DomaineRepository extends JpaRepository<Domaine,Long>{
    
        boolean existsByNomDomaine(String nomDomaine);

}
