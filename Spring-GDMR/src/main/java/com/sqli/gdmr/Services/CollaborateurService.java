package com.sqli.gdmr.Services;

import com.sqli.gdmr.Models.Collaborateur;
import com.sqli.gdmr.Models.DossierMedical;

import java.util.List;

public interface CollaborateurService {
    List<Collaborateur> findAllCollaborateurs();

    Collaborateur getCollaborateurById(Long collaborateurId);

    List<Collaborateur> findCollaborateursWithoutCreneau();


    Collaborateur findById(Long collaborateurId);

    Collaborateur updateProfile(Long collaborateurId, String departement, DossierMedical updatedDossier);
}
