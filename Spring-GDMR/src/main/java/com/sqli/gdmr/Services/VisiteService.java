package com.sqli.gdmr.Services;


import com.sqli.gdmr.Enums.StatusVisite;
import com.sqli.gdmr.Enums.TypesVisite;
import com.sqli.gdmr.Models.Visite;

import java.util.List;

public interface VisiteService {

    List<Visite> getVisitsByMedecinAndStatus(Long medecinId, StatusVisite status);

    Visite createVisite(Long creneauId, Long collaborateurId, String motif);

    Visite createVisiteFromCreneau(Long creneauId, Long collaborateurId);
    List<Visite> getVisitsToConfirm(Long chargéRHId);
    Visite confirmVisit(Long visiteId, String motif, TypesVisite typesVisite);
    void cancelVisit(Long visiteId);
    long getPlannedAppointmentsForToday();
    long getPlannedAppointmentsForWeek();
    List<Visite> getTodayPlannedAppointments();
    boolean isVisitPlannedForCollaborateur(Long collaborateurId);
    List<Visite> getVisitsByChargéRH(Long chargéRHId);
    List<Visite> getVisitesByCollaborateurId(Long collaborateurId);
    Visite findByCollaborateurIdAndStatus(Long collaborateurId, StatusVisite status);
    Visite updateVisite(Visite visite);


}
