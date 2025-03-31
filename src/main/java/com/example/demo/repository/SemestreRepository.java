package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Niveau;
import com.example.demo.model.Semestre;

@Repository
public interface SemestreRepository  extends JpaRepository<Semestre,Long>{


    List<Semestre> findByNiveauId(Long niveauId);
    List<Semestre> findByNomSemestre(String nomSemestre);
    boolean existsByNomSemestreAndNiveau(String nomSemestre, Niveau niveau);
    boolean existsByNomSemestreAndNiveauId(String nomSemestre, Long niveauId);
    


   
}
