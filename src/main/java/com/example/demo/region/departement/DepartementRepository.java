package com.example.demo.region.departement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.region.Region;

@Repository
public interface DepartementRepository  extends JpaRepository<Departement,Long>{

    List<Departement> findByRegionId(Long regionId);

    boolean existsByNomDepartementAndRegionId(String nomDepartement,Long regionId);
    boolean existsByNomDepartementAndRegion(String nomDepartement,Region region);

}

    