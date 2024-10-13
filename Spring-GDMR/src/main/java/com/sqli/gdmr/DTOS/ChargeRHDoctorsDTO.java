package com.sqli.gdmr.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ChargeRHDoctorsDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String specialite;
    private boolean availability;
    private String site;
    private String exp;
}
