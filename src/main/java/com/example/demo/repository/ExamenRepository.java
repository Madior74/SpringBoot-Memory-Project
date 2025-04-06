package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CourseModule;
import com.example.demo.model.Etudiant;
import com.example.demo.model.Examen;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
    List<Examen> findByEtudiantAndCourseModule(Etudiant etudiant, CourseModule courseModule);
}