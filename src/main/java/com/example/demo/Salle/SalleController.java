package com.example.demo.Salle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/salles")
public class SalleController {

    @Autowired
    private SalleService salleService;

    @Autowired
    private SalleRepository salleRepository;

    //get
    @GetMapping
    public List<Salle> getAllSalles(){
        return salleService.getAllSalles();
    }
    

    //Create
   @PostMapping("/save")
public ResponseEntity<?> creerSalle(@RequestBody Salle salle, BindingResult result) {
    if (result.hasErrors()) {
        return ResponseEntity.badRequest().body(result.getAllErrors());
    }
    salleRepository.save(salle);
    return ResponseEntity.ok(salle);
}

}
