package com.sqli.gdmr.Services;



import com.sqli.gdmr.Models.Antecedant;

import java.util.List;

public interface AntecedantService {
    List<Antecedant> getAntecedantsByDossierMedical(Long dossierMedicalId);
    Antecedant addAntecedant(Antecedant antecedant);

}
