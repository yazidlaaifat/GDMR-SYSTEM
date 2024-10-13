package com.sqli.gdmr.DTOS;

import lombok.Data;

import java.util.Date;

@Data
public class AntecedantDTO {
    private Long idAntecedant;
    private Date dateAntecedant;
    private String description;
    private Long dossierMedicalId;
}
