package com.sqli.gdmr.Mapper;

import com.sqli.gdmr.DTOS.AntecedantCollabDTO;
import com.sqli.gdmr.DTOS.CollaborateurProfileDTO;
import com.sqli.gdmr.DTOS.DossierMedicalCollabDTO;
import com.sqli.gdmr.Models.Antecedant;
import com.sqli.gdmr.Models.Collaborateur;
import com.sqli.gdmr.Models.DossierMedical;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CollaborateurProfileMapper {

    public CollaborateurProfileDTO toDTO(Collaborateur collaborateur) {
        CollaborateurProfileDTO dto = new CollaborateurProfileDTO();
        dto.setDepartement(collaborateur.getDepartement());
        dto.setDossierMedical(toDossierMedicalDTO(collaborateur.getDossierMedical()));

        return dto;
    }

    private DossierMedicalCollabDTO toDossierMedicalDTO(DossierMedical dossierMedical){
        if(dossierMedical == null) return null;
        DossierMedicalCollabDTO dto = new DossierMedicalCollabDTO();
        dto.setIdDossierMedical(dossierMedical.getIdDossierMedical());
        dto.setSexe(dossierMedical.getSexe());
        dto.setHeight(dossierMedical.getHeight());
        dto.setWeight(dossierMedical.getWeight());
        dto.setGroupeSanguin(dossierMedical.getGroupeSanguin());
        dto.setAllergies(dossierMedical.getAllergies());
        dto.setMedicalHistory(dossierMedical.getMedicalHistory());
        dto.setMedications(dossierMedical.getMedications());
        dto.setAntecedants(mapAntecedants(dossierMedical.getAntecedants()));
        return dto;
    }

    private List<AntecedantCollabDTO> mapAntecedants(List<Antecedant> antecedants){
        return antecedants.stream().map(this::toAntecedantDTO).collect(Collectors.toList());
    }

    private AntecedantCollabDTO toAntecedantDTO(Antecedant antecedant){
        AntecedantCollabDTO dto = new AntecedantCollabDTO();
        dto.setIdAntecedant(antecedant.getIdAntecedant());
        dto.setDateAntecedant(antecedant.getDateAntecedant()!= null ? antecedant.getDateAntecedant().toString() : null);
        dto.setDescription(antecedant.getDescription());
        return dto;
    }

}
