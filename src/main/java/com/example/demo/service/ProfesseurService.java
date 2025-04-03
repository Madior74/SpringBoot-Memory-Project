package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CourseModule;
import com.example.demo.model.Professeur;
import com.example.demo.model.Role;
import com.example.demo.model.Specialite;
import com.example.demo.repository.ModuleRepository;
import com.example.demo.repository.ProfesseurRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.SpecialiteRepository;

import jakarta.transaction.Transactional;

@Service
public class ProfesseurService {


    @Autowired
    private ProfesseurRepository professeurRepository;
    
   @Autowired
   private RoleRepository repository;

    @Autowired
    private  SpecialiteRepository specialiteRepository;
    


    //Recuperer tous les profs
    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll();
    }

    //Recuperer un prof par son id
    public Professeur getProfesseurById(Long id) {
        return professeurRepository.findById(id).orElse(null);
    }

    //Ajouter un prof

public Professeur createProfesseur(Professeur professeur, List<Long> modulesIds) {
    if (modulesIds != null && !modulesIds.isEmpty()) {
        List<Specialite> spec = specialiteRepository.findAllById(modulesIds);
        professeur.setSpecialites(spec);
    }
   
    return professeurRepository.save(professeur);
}


    //Modifier un prof
    public Professeur updateProfesseur(Long id,Professeur professeurDetails,List<Long>modulesIds){
        Professeur professeur =professeurRepository.findById(id).orElse(null);

        if(professeur !=null){
            professeur.setNom(professeurDetails.getNom());
            professeur.setPrenom(professeurDetails.getPrenom());
            professeur.setAdresse(professeurDetails.getAdresse());
            professeur.setPaysDeNaissance(professeurDetails.getPaysDeNaissance());
            professeur.setDateDeNaissance(professeurDetails.getDateDeNaissance());
            professeur.setImagePath(professeurDetails.getImagePath());
            professeur.setCni(professeurDetails.getCni());
            professeur.setIne(professeurDetails.getIne());
            professeur.setDepartement(professeurDetails.getDepartement());
            if(modulesIds !=null &&!modulesIds.isEmpty()){
                List<Specialite> spec=specialiteRepository.findAllById(modulesIds);
            }
            return professeurRepository.save(professeur);
        }
        return null;
    }



    //Supprimer un prof
    public void deleteProfesseur(Long id){
        professeurRepository.deleteById(id);
    }

}   


