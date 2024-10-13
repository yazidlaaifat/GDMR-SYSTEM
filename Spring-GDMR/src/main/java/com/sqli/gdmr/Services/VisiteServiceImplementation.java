package com.sqli.gdmr.Services;


import com.sqli.gdmr.Enums.StatusVisite;
import com.sqli.gdmr.Enums.TypesVisite;
import com.sqli.gdmr.Exceptions.ResourceNotFoundException;
import com.sqli.gdmr.Models.Collaborateur;
import com.sqli.gdmr.Models.Créneau;
import com.sqli.gdmr.Models.Visite;
import com.sqli.gdmr.Repositories.CollaborateurRepository;
import com.sqli.gdmr.Repositories.CréneauRepository;
import com.sqli.gdmr.Repositories.VisiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VisiteServiceImplementation implements VisiteService {

    @Autowired
    private VisiteRepository repo;

    @Autowired
    private CréneauRepository creneauRepository;

    @Autowired
    private CollaborateurRepository collaborateurRepository;

    @Autowired
    private CreneauService creneauService;

    @Autowired
    private CollaborateurService collaborateurService;

    @Autowired
    private VisiteRepository visiteRepository;



    @Override
    public List<Visite> getVisitsByMedecinAndStatus(Long medecinId, StatusVisite status) {
        return repo.findByMedecin_IdUserAndStatusVisite(medecinId, status);
    }

    @Override
    public Visite createVisite(Long creneauId, Long collaborateurId, String motif) {
        Créneau créneau = creneauRepository.findById(creneauId)
                .orElseThrow(() -> new ResourceNotFoundException("Creneau not found"));

        Collaborateur collaborateur = collaborateurRepository.findById(collaborateurId)
                .orElseThrow(() -> new ResourceNotFoundException("Collaborateur not found"));

        Visite visite = new Visite();
        visite.setCréneau(créneau);
        visite.setCollaborateur(collaborateur);
        visite.setMotif(motif);

        repo.save(visite);

        return visite;
    }

    @Override
    public Visite createVisiteFromCreneau(Long creneauId, Long collaborateurId) {
        Créneau selectedCreneau = creneauService.getCreneauById(creneauId);
        Collaborateur collaborateur = collaborateurService.getCollaborateurById(collaborateurId);

        Visite visite = new Visite();
        visite.setCréneau(selectedCreneau);
        visite.setCollaborateur(collaborateur);
        visite.setMedecin(selectedCreneau.getMedecincren());
        visite.setDateVisite(new Date(selectedCreneau.getDateDebut().getTime()));
        repo.save(visite);

        selectedCreneau.setDisponibilité(false);
        creneauService.saveCreneau(selectedCreneau);

        return visite;
    }

    @Override
    public List<Visite> getVisitsToConfirm(Long chargéRHId) {
        return visiteRepository.findByCréneau_ChargéRH_IdUserAndMotifIsNullAndStatusVisiteIsNullAndTypesVisiteIsNull(chargéRHId);
    }

    @Override
    public Visite confirmVisit(Long visiteId, String motif, TypesVisite typesVisite) {
        Visite visite = visiteRepository.findById(visiteId).orElseThrow(() -> new RuntimeException("Visite not found"));
        visite.setMotif(motif);
        visite.setTypesVisite(typesVisite);
        visite.setStatusVisite(StatusVisite.Planifié);
        return visiteRepository.save(visite);
    }

    @Override
    public void cancelVisit(Long visiteId) {
        Visite visite = visiteRepository.findById(visiteId).orElseThrow(() -> new RuntimeException("Visite not found"));
        Créneau creneau = visite.getCréneau();
        creneau.setChargéRH(null);
        creneau.setCollaborateurcren(null);
        creneau.setDisponibilité(true);
        creneauRepository.save(creneau);
        visiteRepository.delete(visite);

    }

    @Override
    public long getPlannedAppointmentsForToday() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);
        return visiteRepository.findByStatusVisiteAndDateVisiteBetween(StatusVisite.Planifié, startOfDay, endOfDay).size();
    }

    @Override
    public long getPlannedAppointmentsForWeek() {
        LocalDate startOfWeek = LocalDate.now().with(DayOfWeek.MONDAY);
        LocalDateTime startOfWeekDateTime = startOfWeek.atStartOfDay();
        LocalDateTime endOfWeekDateTime = startOfWeekDateTime.plusDays(7).minusSeconds(1);
        return visiteRepository.findByStatusVisiteAndDateVisiteBetween(StatusVisite.Planifié, startOfWeekDateTime,endOfWeekDateTime).size();
    }

    @Override
    public List<Visite> getTodayPlannedAppointments() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusSeconds(1);
        return visiteRepository.findByStatusVisiteAndDateVisiteBetween(StatusVisite.Planifié, startOfDay, endOfDay);
    }

    @Override
    public boolean isVisitPlannedForCollaborateur(Long collaborateurId) {
        return visiteRepository.existsByCollaborateur_IdUserAndStatusVisite(collaborateurId, StatusVisite.Planifié);
    }

    @Override
    public List<Visite> getVisitsByChargéRH(Long chargéRHId) {
        return visiteRepository.findAll().stream()
                .filter(visite -> visite.getCréneau() != null &&
                            visite.getCréneau().getChargéRH() != null &&
                            visite.getCréneau().getChargéRH().getIdUser().equals(chargéRHId))
                 .collect(Collectors.toList());
    }

    @Override
    public List<Visite> getVisitesByCollaborateurId(Long collaborateurId) {
        return visiteRepository.findByCollaborateur_IdUser(collaborateurId);
    }

    @Override
    public Visite findByCollaborateurIdAndStatus(Long collaborateurId, StatusVisite status) {
        return visiteRepository.findFirstByCollaborateur_IdUserAndStatusVisite(collaborateurId, status)
                .orElse(null);
    }

    @Override
    public Visite updateVisite(Visite visite) {
        return visiteRepository.save(visite);
    }

}
