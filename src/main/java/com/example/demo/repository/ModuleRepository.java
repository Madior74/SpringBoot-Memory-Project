package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CourseModule;


public interface ModuleRepository extends JpaRepository<CourseModule, Long> {

    List<CourseModule> findByUe_Id(Long ueId);

    boolean existsByNomModuleAndUe_Id(String nomModule, Long ueId);


    Optional<CourseModule> findByNomModule(String nomModule);


    
}