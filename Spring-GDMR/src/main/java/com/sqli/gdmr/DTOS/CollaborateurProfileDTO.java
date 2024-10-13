package com.sqli.gdmr.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollaborateurProfileDTO {
    private String departement;
    private DossierMedicalCollabDTO dossierMedical;
}
