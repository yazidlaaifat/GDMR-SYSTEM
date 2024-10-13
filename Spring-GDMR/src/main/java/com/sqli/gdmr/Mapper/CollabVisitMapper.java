package com.sqli.gdmr.Mapper;

import com.sqli.gdmr.DTOS.CollabVisitDTO;
import com.sqli.gdmr.Models.Visite;
import org.springframework.stereotype.Component;

@Component
public class CollabVisitMapper {

    public CollabVisitDTO toDTO(Visite visite) {
        CollabVisitDTO dto = new CollabVisitDTO();
        dto.setIdVisite(visite.getIdVisite());
        dto.setMotif(visite.getMotif());
        dto.setStatusVisite(visite.getStatusVisite());
        dto.setTypesVisite(visite.getTypesVisite());
        dto.setDateVisite(visite.getDateVisite());

        if (visite.getMedecin() != null){
            dto.setMedecinNom(visite.getMedecin().getNom());
            dto.setMedecinPrenom(visite.getMedecin().getPrenom());
        }
        return dto;
    }
}
