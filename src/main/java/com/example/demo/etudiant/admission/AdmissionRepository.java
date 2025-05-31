package com.example.demo.etudiant.admission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface  AdmissionRepository extends JpaRepository<Admission,Long>{

    List<Admission> findByStatut(String status);
                boolean existsByEtudiantId(Long etudiantId);

}
