package com.sqli.gdmr.DTOS;

import com.sqli.gdmr.Enums.Role;
import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {
    private Long idUser;
    private String username;
    private String password;
    private String nom;
    private String prenom;
    private Role role;
    private Date dateNaissance;
}
