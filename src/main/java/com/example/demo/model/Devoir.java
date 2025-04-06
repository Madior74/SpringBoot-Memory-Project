package com.example.demo.model;


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