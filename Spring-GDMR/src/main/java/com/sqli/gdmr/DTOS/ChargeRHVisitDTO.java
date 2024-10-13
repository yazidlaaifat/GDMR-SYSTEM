package com.sqli.gdmr.DTOS;

import lombok.Data;

@Data
public class ChargeRHVisitDTO {
    private String dateVisite;
    private String motif;
    private String statusVisite;
    private String typesVisite;
    private String collaborateurFullName;
    private String collaborateurDepartement;
    private String medecinFullName;
}
