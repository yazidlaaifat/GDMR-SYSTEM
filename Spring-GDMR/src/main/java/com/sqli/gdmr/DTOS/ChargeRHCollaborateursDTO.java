package com.sqli.gdmr.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChargeRHCollaborateursDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String departement;
}
