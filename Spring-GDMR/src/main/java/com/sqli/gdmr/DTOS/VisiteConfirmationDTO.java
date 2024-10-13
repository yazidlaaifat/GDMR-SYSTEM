package com.sqli.gdmr.DTOS;

import com.sqli.gdmr.Enums.StatusVisite;
import com.sqli.gdmr.Enums.TypesVisite;
import lombok.Data;

import java.util.Date;

@Data
public class VisiteConfirmationDTO {
    private Long idVisite;
    private Date dateVisite;
    private String motif;
    private StatusVisite statusVisite;
    private TypesVisite typesVisite;
    private String collaborateurNom;
    private String collaborateurPrenom;

}
