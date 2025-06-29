package com.example.demo.note.devoir;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.demo.Seance.Seance;
import com.example.demo.etudiant.prinscription.Etudiant;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

   
@Table(name = "devoir")
public class Devoir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seance_id")
    @JsonBackReference
    private Seance seance;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDevoir;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateAttribution;
}
