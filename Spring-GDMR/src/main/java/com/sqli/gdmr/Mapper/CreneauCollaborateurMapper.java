package com.sqli.gdmr.Mapper;

import com.sqli.gdmr.DTOS.CreneauCollaborateurDTO;
import com.sqli.gdmr.Models.Créneau;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreneauCollaborateurMapper {

    public CreneauCollaborateurDTO toDTO(Créneau creneau) {
        CreneauCollaborateurDTO dto = new CreneauCollaborateurDTO();
        dto.setIdCreneau(creneau.getIdCréneau());
        dto.setDisponibilité(creneau.isDisponibilité());
        dto.setDateDebut(creneau.getDateDebut());
        dto.setDateFin(creneau.getDateFin());

        if(creneau.getChargéRH() != null){
            dto.setChargeRHId(creneau.getChargéRH().getIdUser());
            dto.setChargeRHUsername(creneau.getChargéRH().getUsername());
        }

        return dto;
    }

    public List<CreneauCollaborateurDTO> toDTOList(List<Créneau> creneaux){
        return creneaux.stream()
                        .map(this::toDTO)
                        .collect(Collectors.toList());
    }
}
