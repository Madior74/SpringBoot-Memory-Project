package com.example.demo.etudiant.admission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.etudiant.Etudiant;
import com.example.demo.etudiant.EtudiantRepository;

@Service
public class DossierAdmissionService {

    @Autowired
    private DossierAdmissionRepository dossierAdmissionRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    //Recuperer tous les dossiers
    public List<DossierAdmission> getAllDossierAdmission() {
        return dossierAdmissionRepository.findAllWithEtudiant();
    }
    
    //Recuperer Un dossier
    public DossierAdmission getDossierAdmissionById(Long id){
        return dossierAdmissionRepository.findById(id).orElseThrow(() -> new RuntimeException("DossierAdmission non Trouvé"));
    }
    

    //Recuperer les dosssiers par  Status
    public List<DossierAdmission> getDossierAdmissionByStatus(String status){
        return dossierAdmissionRepository.findByStatut(status);
    }

    //Creer un Nouveau Dossier d'Admission
    public DossierAdmission createDossier(DossierAdmission dossier, Long etudiantId) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
            .orElseThrow(() -> new RuntimeException("Etudiant non trouvé"));
    
        dossier.setEtudiant(etudiant);
        return dossierAdmissionRepository.save(dossier);
    }
    




    //Supprimer un Dossier
    public void deleteDossierAdmission(Long id){
        dossierAdmissionRepository.deleteById(id);
    }


    //Verification de l'existence d'un dossier
    public  boolean existsByEtudiant(Long etudiantId){
        return  dossierAdmissionRepository.existsByEtudiantId(etudiantId);
    }
}
