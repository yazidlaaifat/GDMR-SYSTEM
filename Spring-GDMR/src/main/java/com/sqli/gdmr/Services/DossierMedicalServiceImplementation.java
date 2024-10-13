package com.sqli.gdmr.Services;



import com.sqli.gdmr.Exceptions.ResourceNotFoundException;
import com.sqli.gdmr.Models.DossierMedical;
import com.sqli.gdmr.Repositories.DossierMedicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DossierMedicalServiceImplementation implements DossierMedicalService{
    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;



    @Override
    public DossierMedical getDossierMedicalByCollaborateurId(Long collaborateurId) {
        DossierMedical dossierMedical = dossierMedicalRepository.findByCollaborateur_IdUser(collaborateurId);
        return dossierMedical;
    }

    @Override
    public DossierMedical createDossierMedical() {
        DossierMedical dossierMedical = new DossierMedical();
        return dossierMedicalRepository.save(dossierMedical);
    }

    @Override
    public Long getCollaborateurIdByDossierMedicalId(Long dossierMedicalId) {
        return dossierMedicalRepository.findCollaborateur_IdUserByIdDossierMedical(dossierMedicalId)
                .orElseThrow(() -> new ResourceNotFoundException("Dossier medical not found for ID: " + dossierMedicalId));
    }



    }



