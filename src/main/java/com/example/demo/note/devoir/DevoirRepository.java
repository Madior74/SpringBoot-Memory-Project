package com.example.demo.note.devoir;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.coursModule.CourseModule;
import com.example.demo.etudiant.Etudiant;

@Repository
public interface  DevoirRepository extends JpaRepository<Devoir, Long> {
    List<Devoir> findByEtudiantId(Long etudiantId);
    List<Devoir> findByCourseModuleId(Long coursModuleId);
    List<Devoir> findByEtudiantAndCourseModule(Etudiant etudiant, CourseModule courseModule);
    boolean existsByEtudiantAndCourseModuleAndDateAttribution(Etudiant etudiant, CourseModule courseModule, Date dateAttribution);

}
