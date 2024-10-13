package com.sqli.gdmr.DTOS;

import lombok.Data;



@Data
public class DossierMedicalDTO {
    private Long idDossierMedical;
    private String sexe;
    private int height;
    private int weight;
    private String groupeSanguin;
    private String allergies;

}
