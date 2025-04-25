package com.example.demo.etudiant.admission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface  DossierAdmissionRepository extends JpaRepository<DossierAdmission,Long>{

    List<DossierAdmission> findByStatut(String status);
                boolean existsByEtudiantId(Long etudiantId);

            @Query("SELECT d FROM DossierAdmission d JOIN FETCH d.etudiant")
List<DossierAdmission> findAllWithEtudiant();


}
