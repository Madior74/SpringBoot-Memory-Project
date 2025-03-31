package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Specialite;

public interface  SpecialiteRepository extends JpaRepository<Specialite,Long> {


List<Specialite> findByDomaineId(Long domaineId);
boolean existsByNomSpecialiteAndDomaineId(String nomSpecialite, Long domaineId);
    
}
