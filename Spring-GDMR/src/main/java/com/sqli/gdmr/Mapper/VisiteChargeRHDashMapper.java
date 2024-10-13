package com.sqli.gdmr.Mapper;

import com.sqli.gdmr.DTOS.VisiteChargeRHDashDTO;
import com.sqli.gdmr.Models.Visite;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VisiteChargeRHDashMapper {

    public VisiteChargeRHDashDTO toDTO(Visite visite){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        return new VisiteChargeRHDashDTO(
                dateFormat.format(visite.getDateVisite()),
     visite.getCollaborateur().getNom() + " " + visite.getCollaborateur().getPrenom(),
      visite.getMedecin().getNom() + " " + visite.getMedecin().getPrenom(),
                 visite.getMotif()
        );
    }

    public List<VisiteChargeRHDashDTO> toDTOs(List<Visite> visites) {
        return visites.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
