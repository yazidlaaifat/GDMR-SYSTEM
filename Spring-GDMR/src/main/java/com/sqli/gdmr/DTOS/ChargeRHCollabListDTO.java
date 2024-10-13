package com.sqli.gdmr.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChargeRHCollabListDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String departement;
    //diff
    private boolean isvisitplanned;
}
