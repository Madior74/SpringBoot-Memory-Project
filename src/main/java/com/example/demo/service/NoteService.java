package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }


    //get All notes
    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    //Save
    
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNoteDevoir(Long id, Double noteDevoir) {
        Optional<Note> noteOpt = noteRepository.findById(id);
        if (noteOpt.isPresent()) {
            Note note = noteOpt.get();
            note.setNoteDevoir(noteDevoir);
            return noteRepository.save(note);
        }
        return null;
    }

    public Note updateNoteExamen(Long id, Double noteExamen) {
        Optional<Note> noteOpt = noteRepository.findById(id);
        if (noteOpt.isPresent()) {
            Note note = noteOpt.get();
            note.setNoteExamen(noteExamen);
            return noteRepository.save(note);
        }
        return null;
    }



    public List<Note> getNotesByEtudiant(Long etudiantId) {
        return noteRepository.findByEtudiantId(etudiantId);
    }

    public List<Note> getNotesByModule(Long moduleId) {
        return noteRepository.findByModuleId(moduleId);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}