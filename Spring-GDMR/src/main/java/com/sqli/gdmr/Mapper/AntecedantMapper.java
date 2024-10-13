package com.sqli.gdmr.Mapper;


import com.sqli.gdmr.DTOS.AntecedantDTO;
import com.sqli.gdmr.Models.Antecedant;
import com.sqli.gdmr.Models.DossierMedical;
import org.springframework.stereotype.Component;

@Component
public class AntecedantMapper {
    public AntecedantDTO toDTO(Antecedant antecedant){
        AntecedantDTO dto=new AntecedantDTO();
        dto.setIdAntecedant(antecedant.getIdAntecedant());
        dto.setDateAntecedant(antecedant.getDateAntecedant());
        dto.setDescription(antecedant.getDescription());
        dto.setDossierMedicalId(antecedant.getDossierMedical().getIdDossierMedical());
        return dto;
    }

    public Antecedant reverseDTO(AntecedantDTO antecedantDTO){
        Antecedant antecedant=new Antecedant();
        antecedant.setDateAntecedant(antecedantDTO.getDateAntecedant());
        antecedant.setIdAntecedant(antecedantDTO.getIdAntecedant());
        antecedant.setDescription(antecedantDTO.getDescription());

        DossierMedical dossierMedical=new DossierMedical();
        dossierMedical.setIdDossierMedical(antecedantDTO.getDossierMedicalId());
        antecedant.setDossierMedical(dossierMedical);

        return antecedant;
    }
}
