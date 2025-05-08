package com.example.demo.admin;

import com.example.demo.enums.Role;
import com.example.demo.utilisateur.Utilisateur;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "admins")
@Getter
@Setter
public class Admin extends Utilisateur {


    //Role de l'Admin
    @Override
    public Role getRole(){
        return Role.ROLE_ADMIN;
    }
}