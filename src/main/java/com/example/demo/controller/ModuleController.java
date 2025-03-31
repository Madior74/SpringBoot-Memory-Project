package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CourseModule;

import com.example.demo.repository.ModuleRepository;
import com.example.demo.service.ModuleService;


import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/modules")
public class ModuleController {


    @Autowired
    private ModuleService moduleService;

    @Autowired
    public ModuleRepository moduleRepository;


    //recuperer tous les Modules
    @GetMapping()
    public ResponseEntity<List<CourseModule>> getAllModules(){
        List<CourseModule> courseModules=moduleRepository.findAll();
        return new ResponseEntity<>(courseModules,HttpStatus.OK);
    }


    //Recuperer un Module  par son id
    @GetMapping("/{id}")
   public ResponseEntity<CourseModule> getModuleById(@PathVariable("id")Long id){
    Optional<CourseModule> moduleData=moduleRepository.findById(id);
    if(moduleData.isPresent()){
        return new ResponseEntity<>(moduleData.get(),HttpStatus.OK);

    }else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
   }
    //get Module By UE
    @GetMapping("/ue/{ueId}")
    public List<CourseModule> getModulesByUe(@PathVariable Long ueId){
        return moduleService.getCourseModulesByUe(ueId);
    }
   
    

//    //creer une nouveau Module
@PostMapping("/{ueId}/module")
public ResponseEntity<String> addModuleToUE(@PathVariable Long ueId, @RequestBody CourseModule module) {
    try {
        moduleService.addModuleToUE(ueId, module);
        return ResponseEntity.status(HttpStatus.CREATED).body("Module ajouté avec succès à l'UE");
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'ajout du module : " + e.getMessage());
    }
}



   ///Supprimer Un Module
    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable Long id) {
        moduleService.deleteModule(id);
    }




    //Existence 
    @GetMapping("/exists")
    public ResponseEntity<Boolean> checkCoursModuleExist(@RequestParam String nomModule,@RequestParam Long ueId){
        boolean exists=moduleService.moduleExist(nomModule, ueId);
        return ResponseEntity.ok(exists);
    }
    
}
