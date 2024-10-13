package com.sqli.gdmr.Mapper;

import com.sqli.gdmr.DTOS.VisiteConfirmationDTO;
import com.sqli.gdmr.Models.Visite;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VisiteConfirmationMapper {

    public VisiteConfirmationDTO toDTO(Visite visite){
        if (visite == null) {
            return null;
        }

        VisiteConfirmationDTO dto = new VisiteConfirmationDTO();
        dto.setIdVisite(visite.getIdVisite());
        dto.setDateVisite(visite.getDateVisite());
        dto.setMotif(visite.getMotif());
        dto.setStatusVisite(visite.getStatusVisite());
        dto.setTypesVisite(visite.getTypesVisite());

        if(visite.getCollaborateur() != null) {
            dto.setCollaborateurNom(visite.getCollaborateur().getNom());
            dto.setCollaborateurPrenom(visite.getCollaborateur().getPrenom());
        }

        return dto;
    }

    public List<VisiteConfirmationDTO> toDTOs(List<Visite> visites){
        if (visites == null) {
            return null;
        }

        return visites.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
