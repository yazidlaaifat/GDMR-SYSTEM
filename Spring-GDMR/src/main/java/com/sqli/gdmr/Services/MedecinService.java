package com.sqli.gdmr.Services;


import com.sqli.gdmr.Models.Medecin;

import java.util.List;
import java.util.Optional;

public interface MedecinService {
    Medecin updateMedecin(Long id, Medecin medecinDetails);

    Optional<Medecin> getMedecinById(Long id);

    List<Medecin> findAllDoctors();



}
