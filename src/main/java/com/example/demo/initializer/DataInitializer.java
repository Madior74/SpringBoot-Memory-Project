package com.example.demo.initializer;

import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;

import com.example.demo.region.Region;
import com.example.demo.region.RegionRepository;
import com.example.demo.region.departement.Departement;
import com.example.demo.region.departement.DepartementRepository;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RegionRepository regionRepository;
    private final DepartementRepository departementRepository;

    public DataInitializer(RegionRepository regionRepository, DepartementRepository departementRepository) {
        this.regionRepository = regionRepository;
        this.departementRepository = departementRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (regionRepository.count() == 0) {
            // Création des régions du Sénégal
            Region dakar = new Region("Dakar");
            Region thies = new Region("Thiès");
            Region saintLouis = new Region("Saint-Louis");
            Region ziguinchor = new Region("Ziguinchor");
            Region kaolack = new Region("Kaolack");

            regionRepository.saveAll(List.of(dakar, thies, saintLouis, ziguinchor, kaolack));

            // Création des départements liés aux régions
            Departement dakarDep = new Departement("Dakar", dakar);
            Departement guediawaye = new Departement("Guédiawaye", dakar);
            Departement pikine = new Departement("Pikine", dakar);
            Departement rufisque = new Departement("Rufisque", dakar);

            Departement thiesDep = new Departement("Thiès", thies);
            Departement mbour = new Departement("Mbour", thies);
            Departement tifess = new Departement("Tivaouane", thies);

            Departement saintLouisDep = new Departement("Saint-Louis", saintLouis);
            Departement dagana = new Departement("Dagana", saintLouis);
            Departement podor = new Departement("Podor", saintLouis);

            Departement ziguinchorDep = new Departement("Ziguinchor", ziguinchor);
            Departement bignona = new Departement("Bignona", ziguinchor);
            Departement oussouye = new Departement("Oussouye", ziguinchor);

            Departement kaolackDep = new Departement("Kaolack", kaolack);
            Departement nioro = new Departement("Nioro du Rip", kaolack);
            Departement guinguineo = new Departement("Guinguinéo", kaolack);

            departementRepository.saveAll(List.of(
                    dakarDep, guediawaye, pikine, rufisque,
                    thiesDep, mbour, tifess,
                    saintLouisDep, dagana, podor,
                    ziguinchorDep, bignona, oussouye,
                    kaolackDep, nioro, guinguineo
            ));
        }
    }
}

/* 
ou bien Creer  un script d'initialisation des données
 un fichier Java ou SQL qui insère automatiquement les régions et départements dès que ton application démarre.

En Spring Boot, tu peux utiliser un fichier data.sql dans src/main/resources.

Exemple de data.sql : 

INSERT INTO region (id, name) VALUES (1, 'Nord');
INSERT INTO region (id, name) VALUES (2, 'Sud');

INSERT INTO departement (id, name, region_id) VALUES (1, 'Département A', 1);
INSERT INTO departement (id, name, region_id) VALUES (2, 'Département B', 2);



*/