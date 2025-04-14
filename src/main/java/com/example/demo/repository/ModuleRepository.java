package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.ModuleWithUeDTO;
import com.example.demo.model.CourseModule;
import com.example.demo.model.UE;


public interface ModuleRepository extends JpaRepository<CourseModule, Long> {
      @Query("SELECT new com.example.demo.dto.ModuleWithUeDTO(m.id, m.nomModule, m.volumeHoraire, m.creditModule, m.ue.nomUE) FROM CourseModule m LEFT JOIN m.ue")
    List<ModuleWithUeDTO> findAllWithUeInfo();

    List<CourseModule> findByUe_Id(Long ueId);

    boolean existsByNomModuleAndUe_Id(String nomModule, Long ueId);


    Optional<CourseModule> findByNomModule(String nomModule);

    boolean existsByNomModuleAndUe(String nomModule,UE ue);


    
}