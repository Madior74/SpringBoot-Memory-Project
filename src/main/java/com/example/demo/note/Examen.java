package com.example.demo.note;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Getter
@Setter

@Table(name = "examen")
public class Examen extends Note {

}