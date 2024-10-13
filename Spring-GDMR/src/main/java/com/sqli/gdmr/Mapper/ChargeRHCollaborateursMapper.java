package com.sqli.gdmr.Mapper;

import com.sqli.gdmr.DTOS.ChargeRHCollaborateursDTO;
import com.sqli.gdmr.Models.Collaborateur;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChargeRHCollaborateursMapper {
    public ChargeRHCollaborateursDTO toDTO(Collaborateur collaborateur){
        return new ChargeRHCollaborateursDTO(
                collaborateur.getIdUser(),
                collaborateur.getNom(),
                collaborateur.getPrenom(),
                collaborateur.getDepartement()
        );
    }

    public List<ChargeRHCollaborateursDTO> toDTOs(List<Collaborateur> collaborateurs){
        return collaborateurs.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
