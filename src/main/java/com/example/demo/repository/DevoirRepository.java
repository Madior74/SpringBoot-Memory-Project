package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Devoir;

@Repository
public interface  DevoirRepository extends JpaRepository<Devoir, Long> {
    List<Devoir> findByEtudiantId(Long etudiantId);
    List<Devoir> findByModuleId(Long moduleId);
}
