package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CourseModule;
import com.example.demo.model.Devoir;
import com.example.demo.model.Etudiant;

@Repository
public interface  DevoirRepository extends JpaRepository<Devoir, Long> {
    List<Devoir> findByEtudiantId(Long etudiantId);
    List<Devoir> findByCourseModuleId(Long coursModuleId);
    List<Devoir> findByEtudiantAndCourseModule(Etudiant etudiant, CourseModule courseModule);

}
