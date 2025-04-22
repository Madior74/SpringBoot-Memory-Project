package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Inscription;

public interface InscriptionRepository  extends JpaRepository<Inscription,Long>{
    
}
