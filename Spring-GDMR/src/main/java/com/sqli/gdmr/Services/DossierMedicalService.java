package com.sqli.gdmr.Services;



import com.sqli.gdmr.Models.DossierMedical;


public interface DossierMedicalService {
    DossierMedical getDossierMedicalByCollaborateurId(Long collaborateurId);

    DossierMedical createDossierMedical();

    Long getCollaborateurIdByDossierMedicalId(Long idDossierMedical);

}
