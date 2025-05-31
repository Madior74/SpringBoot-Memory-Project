package com.example.demo.filiere;

import lombok.Data;

import java.util.List;

@Data
public class FiliereRequest {
    private String nomFiliere;
    private String description;
    private List<String> debouches;
    private List<String> prerequis;
    private List<String> admission;
}
