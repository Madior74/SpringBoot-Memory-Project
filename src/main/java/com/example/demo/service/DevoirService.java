package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Devoir;
import com.example.demo.model.Devoir;
import com.example.demo.repository.DevoirRepository;

@Service
public class DevoirService {

    @Autowired
    private DevoirRepository devoirRepository;

    
    
    //get All notes
    public List<Devoir> getAllNotes(){
        return devoirRepository.findAll();
    }

    //Save
    
    public Devoir saveNote(Devoir note) {
        return devoirRepository.save(note);
    }


    //NoteDevor by Id
    public Optional<Devoir> getDevoirById(Long id){
        return devoirRepository.findById(id);
    }

    // public Devoir updateNoteDevoir(Long id, Double noteDevoir) {
    //     Optional<Devoir> noteOpt = devoirRepository.findById(id);
    //     if (noteOpt.isPresent()) {
    //         Devoir note = noteOpt.get();
    //         note.setNoteDevoir(noteDevoir);
    //         return devoirRepository.save(note);
    //     }
    //     return null;
    // }

    // public Devoir updateNoteDevoir(Long id, Double noteDevoir) {
    //     Optional<Devoir> noteOpt = devoirRepository.findById(id);
    //     if (noteOpt.isPresent()) {
    //         Devoir note = noteOpt.get();
    //         note.setNoteDevoir(noteDevoir);
    //         return devoirRepository.save(note);
    //     }
    //     return null;
    // }




    public List<Devoir> getDevoirByEtudiant(Long etudiantId) {
        return devoirRepository.findByEtudiantId(etudiantId);
    }

    public List<Devoir> getDevoirByModule(Long moduleId) {
        return devoirRepository.findByModuleId(moduleId);
    }

    public void deleteDevoir(Long id) {
        devoirRepository.deleteById(id);
    }
}