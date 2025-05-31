package com.example.demo.anneeAcademique;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;


@Repository
public interface AnneeAcademiqueRepository  extends JpaRepository<AnneeAcademique,Long>{
    @Query("SELECT a FROM AnneeAcademique a WHERE :currentDate BETWEEN a.dateDebut AND a.dateFin")
    Optional<AnneeAcademique> findCurrent(@Param("currentDate") LocalDate currentDate);


  

}
