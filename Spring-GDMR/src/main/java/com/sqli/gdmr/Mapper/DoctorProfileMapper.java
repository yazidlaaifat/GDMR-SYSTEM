package com.sqli.gdmr.Mapper;

import com.sqli.gdmr.DTOS.DoctorProfileDTO;
import com.sqli.gdmr.Models.Medecin;
import org.springframework.stereotype.Component;

@Component
public class DoctorProfileMapper {

    public DoctorProfileDTO toDTO(Medecin medecin){
        return new DoctorProfileDTO(
                medecin.getUsername(),
                medecin.getRole().toString(),
                medecin.getSiteTravail(),
                medecin.getSpecialite(),
                medecin.getQualification(),
                medecin.getExperience()
        );
    }
}
