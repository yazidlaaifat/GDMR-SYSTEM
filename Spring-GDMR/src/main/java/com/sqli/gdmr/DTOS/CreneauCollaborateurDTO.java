package com.sqli.gdmr.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreneauCollaborateurDTO {
    private Long idCreneau;
    private boolean disponibilité;
    private Date dateDebut;
    private Date dateFin;

    private Long chargeRHId;
    private String chargeRHUsername;
}
