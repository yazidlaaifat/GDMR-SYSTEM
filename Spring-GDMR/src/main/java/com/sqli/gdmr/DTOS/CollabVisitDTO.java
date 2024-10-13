package com.sqli.gdmr.DTOS;

import com.sqli.gdmr.Enums.StatusVisite;
import com.sqli.gdmr.Enums.TypesVisite;
import lombok.Data;

import java.util.Date;

@Data
public class CollabVisitDTO {
    private Long idVisite;
    private String motif;
    private StatusVisite statusVisite;
    private TypesVisite typesVisite;
    private Date dateVisite;
    private String medecinNom;
    private String medecinPrenom;
}
