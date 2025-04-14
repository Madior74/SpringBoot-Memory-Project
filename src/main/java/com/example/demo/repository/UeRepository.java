package com.example.demo.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Semestre;
import com.example.demo.model.UE;

@Repository
public interface UeRepository extends JpaRepository<UE, Long> {

    List<UE> findBySemestreId(Long semestreId);
    List<UE> findByNomUE(String nomUE);
    boolean existsByNomUEAndSemestre(String nomUE, Semestre semestre);
    boolean existsByNomUEAndSemestreId(String nomUE,Long semestreId);

    
    
}
