package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Domaine;
import com.example.demo.model.Filiere;


@Repository
public interface DomaineRepository extends JpaRepository<Domaine,Long>{
    
        boolean existsByNomDomaine(String nomDomaine);

}
