package com.sqli.gdmr.Mapper;

import com.sqli.gdmr.DTOS.PageVisitesActivesDTO;
import com.sqli.gdmr.Models.Visite;
import org.springframework.stereotype.Component;

@Component
public class PageVisitesActivesMapper {
    public PageVisitesActivesDTO toDTO(Visite visite)
    {
        PageVisitesActivesDTO pageVisitesActivesDTO=new PageVisitesActivesDTO();

        pageVisitesActivesDTO.setIdVisite(visite.getIdVisite());
        pageVisitesActivesDTO.setMotif(visite.getMotif());
        pageVisitesActivesDTO.setNom(visite.getCollaborateur().getNom());
        pageVisitesActivesDTO.setPrenom(visite.getCollaborateur().getPrenom());
        pageVisitesActivesDTO.setIdUser(visite.getCollaborateur().getIdUser());

        pageVisitesActivesDTO.setStatus(visite.getStatusVisite().toString());
        pageVisitesActivesDTO.setTypeVisite(visite.getTypesVisite().toString());
        pageVisitesActivesDTO.setDateVisite(visite.getDateVisite());


        return pageVisitesActivesDTO;
    }
}
