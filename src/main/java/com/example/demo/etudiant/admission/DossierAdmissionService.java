package com.example.demo.etudiant.admission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.etudiant.prinscription.Etudiant;
import com.example.demo.etudiant.prinscription.EtudiantRepository;

@Service
public class DossierAdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    //Recuperer tous les dossiers
    public List<Admission> getAllDossierAdmission() {
        return admissionRepository.findAll();
    }
    
    //Recuperer Un dossier
    public Admission getDossierAdmissionById(Long id){
        return admissionRepository.findById(id).orElseThrow(() -> new RuntimeException("DossierAdmission non Trouvé"));
    }
    

    //Recuperer les dosssiers par  Status
    public List<Admission> getDossierAdmissionByStatus(String status){
        return admissionRepository.findByStatut(status);
    }

    //Creer un Nouveau Dossier d'Admission
    public Admission createDossier(Admission dossier, Long etudiantId) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
            .orElseThrow(() -> new RuntimeException("Etudiant non trouvé"));
    
        dossier.setEtudiant(etudiant);
        return admissionRepository.save(dossier);
    }
    

//Update
public Admission updateDossier(Long id, Admission dossierDetails) {
    Admission dossier = admissionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Dossier non trouvé"));

    dossier.setCopieCni(dossierDetails.isCopieCni());
    dossier.setReleveNotes(dossierDetails.isReleveNotes());
    dossier.setDiplome(dossierDetails.isDiplome());
    dossier.setStatut(dossierDetails.getStatut());
    dossier.setRemarque(dossierDetails.getRemarque());

    if (dossierDetails.getEtudiant() != null) {
        Etudiant etudiant = etudiantRepository.findById(dossierDetails.getEtudiant().getId())
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        dossier.setEtudiant(etudiant);
    }

    return admissionRepository.save(dossier);
}

    //Supprimer un Dossier
    public void deleteDossierAdmission(Long id){
        admissionRepository.deleteById(id);
    }


    //Verification de l'existence d'un dossier
    public  boolean existsByEtudiant(Long etudiantId){
        return  admissionRepository.existsByEtudiantId(etudiantId);
    }
}
