package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DossierAdmission;



@Repository
public interface  DossierAdmissionRepository extends JpaRepository<DossierAdmission,Long>{

    List<DossierAdmission> findByStatut(String status);
                boolean existsByEtudiantId(Long etudiantId);
}
