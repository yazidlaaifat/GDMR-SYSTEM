package com.sqli.gdmr.Models;

import com.sqli.gdmr.Enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String username;
    private String password;
    private String nom;
    private String prenom;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role role;
    private Date dateNaissance;
}
