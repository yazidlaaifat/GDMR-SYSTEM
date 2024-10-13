package com.sqli.gdmr.DTOS;

import lombok.Data;

import java.util.Date;

@Data
public class PageVisitesActivesDTO {
    String Motif;
    String Nom;
    String Prenom;
    String Status;
    String TypeVisite;
    Date DateVisite;
    Long idUser;
    Long idVisite;

}
