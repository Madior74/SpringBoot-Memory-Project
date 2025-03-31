package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Departement;
import com.example.demo.model.Region;

@Repository
public interface DepartementRepository  extends JpaRepository<Departement,Long>{

    List<Departement> findByRegionId(Long regionId);

    boolean existsByNomDepartementAndRegionId(String nomDepartement,Long regionId);
    boolean existsByNomDepartementAndRegion(String nomDepartement,Region region);

}

    