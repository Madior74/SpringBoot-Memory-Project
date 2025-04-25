package com.example.demo.note;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.coursModule.CourseModule;
import com.example.demo.etudiant.Etudiant;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {
    List<Examen> findByEtudiantAndCourseModule(Etudiant etudiant, CourseModule courseModule);
}