package com.sqli.gdmr.Mapper;

import com.sqli.gdmr.DTOS.ChargeRHVisitDTO;
import com.sqli.gdmr.Models.Visite;
import org.springframework.stereotype.Component;

@Component
public class ChargéRHVisitMapper {
    public ChargeRHVisitDTO toDTO(Visite visite) {
        ChargeRHVisitDTO dto = new ChargeRHVisitDTO();
        dto.setDateVisite(visite.getDateVisite().toString());
        dto.setMotif(visite.getMotif());
        dto.setStatusVisite(visite.getStatusVisite() != null ? visite.getStatusVisite().name() : null);
        dto.setTypesVisite(visite.getTypesVisite() != null ? visite.getTypesVisite().name() : null);


        if (visite.getCollaborateur() != null) {
            String collaborateurFullName = visite.getCollaborateur().getNom() + " " + visite.getCollaborateur().getPrenom();
            dto.setCollaborateurFullName(collaborateurFullName);
            dto.setCollaborateurDepartement(visite.getCollaborateur().getDepartement());
        }


        if (visite.getMedecin() != null) {
            String medecinFullName = visite.getMedecin().getNom() + " " + visite.getMedecin().getPrenom();
            dto.setMedecinFullName(medecinFullName);
        }

        return dto;
    }
}
