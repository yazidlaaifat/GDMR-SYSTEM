package com.sqli.gdmr.Mapper;

import com.sqli.gdmr.DTOS.ChargeRHCollabListDTO;
import com.sqli.gdmr.Models.Collaborateur;
import com.sqli.gdmr.Services.VisiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChargeRHCollabListMapper {

    @Autowired
    private VisiteService visiteService;
    public ChargeRHCollabListDTO toDTO(Collaborateur collaborateur) {
        boolean isVisitPlanned = visiteService.isVisitPlannedForCollaborateur(collaborateur.getIdUser());
        return new ChargeRHCollabListDTO(
                collaborateur.getIdUser(),
                collaborateur.getNom(),
                collaborateur.getPrenom(),
                collaborateur.getDepartement(),
                isVisitPlanned
        );
    }

    public List<ChargeRHCollabListDTO> toDTOs(List<Collaborateur> collaborateurs) {
        return collaborateurs.stream().map(this::toDTO).collect(Collectors.toList());
    }
}