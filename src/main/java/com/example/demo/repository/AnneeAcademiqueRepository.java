package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.AnneeAcademique;


@Repository
public interface AnneeAcademiqueRepository  extends JpaRepository<AnneeAcademique,Long>{

}
