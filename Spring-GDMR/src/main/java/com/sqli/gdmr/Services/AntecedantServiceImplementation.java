package com.sqli.gdmr.Services;



import com.sqli.gdmr.Models.Antecedant;
import com.sqli.gdmr.Models.DossierMedical;
import com.sqli.gdmr.Repositories.AntecedantRepository;
import com.sqli.gdmr.Repositories.DossierMedicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AntecedantServiceImplementation implements AntecedantService {
    @Autowired
    private AntecedantRepository antecedantRepository;

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @Override
    public List<Antecedant> getAntecedantsByDossierMedical(Long dossierMedicalId) {
        return antecedantRepository.findByDossierMedical_IdDossierMedical(dossierMedicalId);
    }

    @Override
    public Antecedant addAntecedant(Antecedant antecedant) {
        DossierMedical dossierMedical = dossierMedicalRepository.findById(antecedant.getDossierMedical().getIdDossierMedical()).orElseThrow(() -> new RuntimeException("Dossier Medical not found"));
        Antecedant antecedant1 = new Antecedant();
        antecedant1.setDateAntecedant(antecedant.getDateAntecedant());
        antecedant1.setDescription(antecedant.getDescription());
        antecedant1.setDossierMedical(dossierMedical);
        return antecedantRepository.save(antecedant1);
    }

}
