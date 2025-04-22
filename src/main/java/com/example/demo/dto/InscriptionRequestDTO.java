package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;


@Data
public class InscriptionRequestDTO {
    private Long etudiant;
    private Long filiere;
    private Long niveau;
    private Long anneeAcademique;
    private Long dossierAdmission;
    private LocalDate dateInscription;
    private String montantVerse;
}
