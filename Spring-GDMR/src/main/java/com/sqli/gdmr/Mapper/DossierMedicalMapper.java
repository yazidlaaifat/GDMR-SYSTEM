package com.sqli.gdmr.Mapper;

import com.sqli.gdmr.DTOS.DossierMedicalDTO;
import com.sqli.gdmr.Models.DossierMedical;
import org.springframework.stereotype.Component;

@Component
public class DossierMedicalMapper {

    public DossierMedicalDTO toDTO(DossierMedical dossierMedical) {
        DossierMedicalDTO dto = new DossierMedicalDTO();

        dto.setIdDossierMedical(dossierMedical.getIdDossierMedical());

        dto.setSexe(dossierMedical.getSexe());
        dto.setHeight(dossierMedical.getHeight());
        dto.setWeight(dossierMedical.getWeight());
        dto.setGroupeSanguin(dossierMedical.getGroupeSanguin());
        dto.setAllergies(dossierMedical.getAllergies());
        return dto;
    }

    public DossierMedical toEntity(DossierMedicalDTO dto) {
        DossierMedical dossierMedical = new DossierMedical();
        dossierMedical.setIdDossierMedical(dto.getIdDossierMedical());
        dossierMedical.setSexe(dto.getSexe());
        dossierMedical.setHeight(dto.getHeight());
        dossierMedical.setWeight(dto.getWeight());
        dossierMedical.setGroupeSanguin(dto.getGroupeSanguin());
        dossierMedical.setAllergies(dto.getAllergies());
        return dossierMedical;
    }


}
