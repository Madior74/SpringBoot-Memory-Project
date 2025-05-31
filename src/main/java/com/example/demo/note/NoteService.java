// package com.example.demo.note;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.example.demo.coursModule.CourseModule;
// import com.example.demo.coursModule.ModuleService;
// import com.example.demo.etudiant.prinscription.Etudiant;
// import com.example.demo.note.Examen.Examen;
// import com.example.demo.note.Examen.ExamenRepository;
// import com.example.demo.note.devoir.Devoir;
// import com.example.demo.note.devoir.DevoirRepository;
// @Service
// public class NoteService {

//     @Autowired
//     private DevoirRepository devoirRepository;

//     @Autowired
//     private ExamenRepository examenRepository;

//     @Autowired
//     private ModuleService courseModuleService;

//     // Ajouter une note de devoir
//     public Devoir addDevoir(Devoir devoir) {
//         return devoirRepository.save(devoir);
//     }

//     // Ajouter une note d'examen
//     public Examen addExamen(Examen examen) {
//         return examenRepository.save(examen);
//     }

//     // Récupérer toutes les notes de devoir pour un étudiant dans un module
//     public List<Devoir> getDevoirNotesByStudentAndModule(Long etudiantId, Long moduleId) {
//         Etudiant etudiant = new Etudiant();
//         etudiant.setId(etudiantId);

//         CourseModule module = new CourseModule();
//         module.setId(moduleId);

//         return devoirRepository.findByEtudiantAndCourseModule(etudiant, module);
//     }

//     // Récupérer toutes les notes d'examen pour un étudiant dans un module
//     public List<Examen> getExamenNotesByStudentAndModule(Long etudiantId, Long moduleId) {
//         Etudiant etudiant = new Etudiant();
//         etudiant.setId(etudiantId);

//         CourseModule module = new CourseModule();
//         module.setId(moduleId);

//         return examenRepository.findByEtudiantAndCourseModule(etudiant, module);
//     }

//     // Calculer la moyenne finale pour un étudiant dans un module
//     public Double calculateFinalAverage(Long etudiantId, Long moduleId) {
//         List<Devoir> devoirs = getDevoirNotesByStudentAndModule(etudiantId, moduleId);
//         List<Examen> examens = getExamenNotesByStudentAndModule(etudiantId, moduleId);

//         double averageDevoir = devoirs.stream()
//                 .mapToDouble(Devoir::getNote)
//                 .average()
//                 .orElse(0.0);

//         double averageExamen = examens.stream()
//                 .mapToDouble(Examen::getNote)
//                 .average()
//                 .orElse(0.0);

//         return (averageDevoir * 0.3) + (averageExamen * 0.7);
//     }

//     //Verifier l'existence d'une note de devoir
//       public void addNote(Devoir devoir) {
//         if (devoirRepository.existsByEtudiantAndCourseModuleAndDateAttribution(devoir.getEtudiant(), devoir.getCourseModule(), devoir.getDateAttribution())) {
//             throw new RuntimeException("Cette note de devoir existe déjà pour cet étudiant, ce module et cette date.");
//         }
//         devoirRepository.save(devoir);
//     }

//     //Supprimer une note de devoir
//     public void deletDevoir(Long id){
//          devoirRepository.deleteById(id);
//     }
// }