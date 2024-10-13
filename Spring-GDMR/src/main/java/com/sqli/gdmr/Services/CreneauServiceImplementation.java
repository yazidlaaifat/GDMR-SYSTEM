package com.sqli.gdmr.Services;

import com.sqli.gdmr.Exceptions.ResourceNotFoundException;
import com.sqli.gdmr.Models.*;
import com.sqli.gdmr.Repositories.CollaborateurRepository;
import com.sqli.gdmr.Repositories.CréneauRepository;
import com.sqli.gdmr.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Service
public class CreneauServiceImplementation implements CreneauService {

    @Autowired
    private CréneauRepository creneauRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollaborateurRepository collaborateurRepository;


    @Override
    public List<Créneau> parseCSV(MultipartFile file, Medecin medecin) throws IOException {
        List<Créneau> creneaux = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

            for (CSVRecord csvRecord : csvParser) {
                Créneau créneau = new Créneau();

                Date dateDebut = dateFormat.parse(csvRecord.get("DateDebut"));
                Date dateFin = dateFormat.parse(csvRecord.get("DateFin"));

                créneau.setDateDebut(dateDebut);
                créneau.setDateFin(dateFin);
                créneau.setMedecincren(medecin);

                créneau.setDisponibilité(true);

                creneaux.add(créneau);
            }
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date/time",e);
        }

        return creneaux;
    }

    @Override
    @Transactional
    public void saveCreneaux(List<Créneau> creneaux) {
        for (Créneau créneau : creneaux) {
            boolean exists = creneauRepository.existsByDateDebutAndDateFinAndMedecincren(
                    créneau.getDateDebut(),
                    créneau.getDateFin(),
                    créneau.getMedecincren()
            );

            if (exists) {
                throw new DuplicateKeyException("Créneau with the same dateDebut, dateFin, and doctor already exists!");
            }

            creneauRepository.save(créneau);
        }
    }


    @Override
    @Transactional
    public void assignCreneaux(Long medecinId, Long chargeRHId, Long collaborateurId) {

        Medecin medecin = userRepository.findById(medecinId)
                .filter(Medecin.class::isInstance)
                .map(Medecin.class::cast)
                .orElseThrow(() -> new ResourceNotFoundException("Medecin not found for this id :: "+ medecinId));

        ChargéRH chargéRH = userRepository.findById(chargeRHId)
                .filter(ChargéRH.class::isInstance)
                .map(ChargéRH.class::cast)
                .orElseThrow(() -> new ResourceNotFoundException("ChargéRH not found for this id :: " + chargeRHId));

        Collaborateur collaborateur = userRepository.findById(collaborateurId)
                .filter(Collaborateur.class::isInstance)
                .map(Collaborateur.class::cast)
                .orElseThrow(() -> new ResourceNotFoundException("Collaborateur not found for this id :: "+ collaborateurId));

        List<Créneau> availableCreneaux = creneauRepository.findByMedecincrenAndDisponibilitéTrue(medecin);

        if(availableCreneaux.isEmpty()) {
            throw new ResourceNotFoundException("No available créneaux found for the selected Medecin.");
        }

        for(Créneau creneau: availableCreneaux) {
            creneau.setChargéRH(chargéRH);
            creneau.setCollaborateurcren(collaborateur);
        }

        creneauRepository.saveAll(availableCreneaux);
    }


    @Override
    public void releaseUnchosenCreneaux(Long chosenCreneauId, Long collaborateurId) {
        List<Créneau> creneaux = creneauRepository.findByCollaborateurcren_IdUserAndIdCréneauNot(collaborateurId, chosenCreneauId);
        for (Créneau creneau : creneaux) {
            creneau.setCollaborateurcren(null);
            creneau.setChargéRH(null);
            creneauRepository.save(creneau);
        }
    }

    @Override
    public List<Créneau> getCreneauxByCollaborateurId(Long collaborateurId) {
        return creneauRepository.findByCollaborateurcren_IdUser(collaborateurId);
    }

    @Override
    public Créneau getCreneauById(Long creneauId) {
        return creneauRepository.findById(creneauId)
                .orElseThrow(() -> new RuntimeException("Créneau not found"));
    }

    @Override
    public void saveCreneau(Créneau créneau) {
        creneauRepository.save(créneau);
    }

    @Override
    public List<Créneau> findAvailableCreneaux() {
        return creneauRepository.findByDisponibilitéTrue();
    }

    @Override
    public boolean isDossierMedicalComplete(Long collaborateurId) {
        Collaborateur collaborateur = collaborateurRepository.findById(collaborateurId) .orElseThrow(() -> new RuntimeException("Collaborateur not found"));
        DossierMedical dossier = collaborateur.getDossierMedical();
        if(dossier == null) {
            return false;
        }

        return dossier.getSexe() != null && dossier.getHeight() != 0 &&
                dossier.getWeight() != 0 && dossier.getGroupeSanguin() != null &&
                dossier.getAllergies() != null && dossier.getMedicalHistory() != null &&
                dossier.getMedications() != null;
    }

}