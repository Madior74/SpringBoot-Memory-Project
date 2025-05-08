package com.example.demo.cours;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CoursDTO {

    private Long id;

    private String jour;
    private String heureDebut;
    private String heureFin;
    private LocalDate dateDebut;
    private LocalDate dateFin;

    private String salle;

    private Boolean estRemplacement;

    // Informations liées aux autres entités (en lecture seule)
    private String moduleName;
    private String filiereName;
    private String niveauName;
    private String semestreName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateAjout;
}