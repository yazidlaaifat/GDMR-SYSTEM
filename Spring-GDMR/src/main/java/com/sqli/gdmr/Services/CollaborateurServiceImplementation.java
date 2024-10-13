package com.sqli.gdmr.Services;


import com.sqli.gdmr.Exceptions.ResourceNotFoundException;
import com.sqli.gdmr.Models.Collaborateur;
import com.sqli.gdmr.Models.Créneau;
import com.sqli.gdmr.Models.DossierMedical;
import com.sqli.gdmr.Repositories.CollaborateurRepository;
import com.sqli.gdmr.Repositories.CréneauRepository;
import com.sqli.gdmr.Repositories.DossierMedicalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollaborateurServiceImplementation implements CollaborateurService{

    @Autowired
    private CollaborateurRepository collaborateurRepository;

    @Autowired
    private CréneauRepository créneauRepository;

    @Autowired
    private DossierMedicalRepository dossierMedicalRepository;

    @Override
    public List<Collaborateur> findAllCollaborateurs(){
        return collaborateurRepository.findAll();
    }

    @Override
    public Collaborateur getCollaborateurById(Long collaborateurId) {
        return collaborateurRepository.findById(collaborateurId)
                .orElseThrow(() -> new RuntimeException("Collaborateur not found"));
    }

    @Override
    public List<Collaborateur> findCollaborateursWithoutCreneau() {

        List<Collaborateur> allCollaborateurs = collaborateurRepository.findAll();


        List<Long> attributedCollaborateurIds = créneauRepository.findAll().stream()
                .map(Créneau::getCollaborateurcren)
                .filter(collaborateur -> collaborateur != null)
                .map(Collaborateur::getIdUser)
                .collect(Collectors.toList());


        return allCollaborateurs.stream()
                .filter(collaborateur -> !attributedCollaborateurIds.contains(collaborateur.getIdUser()))
                .collect(Collectors.toList());
    }

    @Override
    public Collaborateur findById(Long collaborateurId) {
        return collaborateurRepository.findById(collaborateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Collaborateur not found with ID: " + collaborateurId));
    }

    @Override
    public Collaborateur updateProfile(Long collaborateurId, String departement, DossierMedical updatedDossier) {
        Collaborateur collaborateur = findById(collaborateurId);

        collaborateur.setDepartement(departement);

        DossierMedical dossier = collaborateur.getDossierMedical();
        if(dossier == null) {
            dossier = new DossierMedical();
            collaborateur.setDossierMedical(dossier);
        }

        dossier.setSexe(updatedDossier.getSexe());
        dossier.setHeight(updatedDossier.getHeight());
        dossier.setWeight(updatedDossier.getWeight());
        dossier.setGroupeSanguin(updatedDossier.getGroupeSanguin());
        dossier.setAllergies(updatedDossier.getAllergies());
        dossier.setMedicalHistory(updatedDossier.getMedicalHistory());
        dossier.setMedications(updatedDossier.getMedications());

        dossierMedicalRepository.save(dossier);
        return collaborateurRepository.save(collaborateur);

    }
}

