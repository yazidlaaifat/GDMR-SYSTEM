package com.sqli.gdmr.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DossierMedicalCollabDTO {

    private Long idDossierMedical;
    private String sexe;
    private Integer height;
    private Integer weight;
    private String groupeSanguin;
    private String allergies;
    private String medicalHistory;
    private String medications;
    private List<AntecedantCollabDTO> antecedants;

}
