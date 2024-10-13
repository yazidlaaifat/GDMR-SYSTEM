package com.sqli.gdmr.Mapper;

import com.sqli.gdmr.DTOS.ChargeRHDoctorsDTO;
import com.sqli.gdmr.Models.Créneau;
import com.sqli.gdmr.Models.Medecin;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChargeRHDoctorsMapper {

    public ChargeRHDoctorsDTO toDTO(Medecin medecin, boolean availability){
        return new ChargeRHDoctorsDTO(
                medecin.getIdUser(),
                medecin.getNom(),
                medecin.getPrenom(),
                medecin.getSpecialite(),
                availability,
                medecin.getSiteTravail(),
                medecin.getExperience()
        );
    }

    public List<ChargeRHDoctorsDTO> toDTOs(List<Medecin> medecins, List<Créneau> availableCreneaux){
        return medecins.stream()
                .map(medecin -> {
                    boolean isAvailable = availableCreneaux.stream()
                            .anyMatch(créneau -> créneau.getMedecincren().equals(medecin));
                    return toDTO(medecin, isAvailable);
                })
                .collect(Collectors.toList());
    }
}
