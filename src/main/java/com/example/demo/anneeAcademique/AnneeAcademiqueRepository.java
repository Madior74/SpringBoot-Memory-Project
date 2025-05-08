package com.example.demo.anneeAcademique;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface AnneeAcademiqueRepository  extends JpaRepository<AnneeAcademique,Long>{


  

}
