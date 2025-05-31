package com.example.demo.ue;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.semestre.Semestre;

@Repository
public interface UeRepository extends JpaRepository<UE, Long> {

    List<UE> findBySemestreId(Long semestreId);
    List<UE> findByNomUE(String nomUE);
    boolean existsByNomUEAndSemestre(String nomUE, Semestre semestre);
    boolean existsByNomUEAndSemestreId(String nomUE,Long semestreId);

    
    
}
