package com.example.demo.note.devoir;


import com.example.demo.note.Note;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

    @Entity
    @Getter
    @Setter

    @Table(name = "devoir")
    public class Devoir extends Note {
    }