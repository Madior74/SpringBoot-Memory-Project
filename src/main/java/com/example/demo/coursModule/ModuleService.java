package com.example.demo.coursModule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ModuleWithUeDTO;
import com.example.demo.ue.UE;
import com.example.demo.ue.UeRepository;

import jakarta.transaction.Transactional;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private UeRepository ueRepository;

  

    //Get All
    public List<CourseModule> getAllModules() {
        return moduleRepository.findAll();
    }

    //get by id
   public Optional<CourseModule> getModuleById(Long moduleId){
    return moduleRepository.findById(moduleId);
   }


   

    //delete
    public void deleteModule(Long id){  
        moduleRepository.deleteById(id);
    }

    //Recuperer les modules pour chaque semestre
    public List<CourseModule> getCourseModulesByUe(Long ueId){

        return moduleRepository.findByUe_Id(ueId);
    }

    //Verification de l'existence d'une UE
    public boolean moduleExist(String nomModule,Long ueId){
        return moduleRepository.existsByNomModuleAndUe_Id(nomModule, ueId);
    }



    // //Ajout
    // @Transactional
    // public void addModuleToUE(Long ueId, CourseModule module) {
    //     Optional<UE> ueOptional = ueRepository.findById(ueId);
    //     if (ueOptional.isPresent()) {
    //         UE ue = ueOptional.get();
    //         module.setUe(ue); // Associer le module à l'UE
    //         module.setDateAjout(LocalDateTime.now());
    //         moduleRepository.save(module); // Sauvegarder le module
    //         ue.addModule(module); // Ajouter le module à l'UE
    //         ueRepository.save(ue); // Sauvegarder l'UE
    //     } else {
    //         throw new RuntimeException("UE not found with id: " + ueId);
    //     }
    // }


    public CourseModule saveModule(CourseModule module) {
        return moduleRepository.save(module);
    }

      public List<ModuleWithUeDTO> getAllModulesWithUe() {
        return moduleRepository.findAllWithUeInfo();
    }


    public void updateModule(Long moduleId, CourseModule moduleDetails) {
        CourseModule module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found with id: " + moduleId));

        module.setNomModule(moduleDetails.getNomModule());
        module.setVolumeHoraire(moduleDetails.getVolumeHoraire());
        module.setCreditModule(moduleDetails.getCreditModule());
        module.setDateAjout(LocalDateTime.now());

        moduleRepository.save(module);
    }


    //
    public boolean existsByNomModuleAndUe(String nomModule,UE ue){
        return moduleRepository.existsByNomModuleAndUe(nomModule ,ue);
    }
}
