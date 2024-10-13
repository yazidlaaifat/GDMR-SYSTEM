package com.sqli.gdmr.Controller;

import com.sqli.gdmr.DTOS.*;
import com.sqli.gdmr.Enums.TypesVisite;
import com.sqli.gdmr.Mapper.*;
import com.sqli.gdmr.Models.Collaborateur;
import com.sqli.gdmr.Models.Créneau;
import com.sqli.gdmr.Models.Medecin;
import com.sqli.gdmr.Models.Visite;
import com.sqli.gdmr.Services.CollaborateurService;
import com.sqli.gdmr.Services.CreneauService;
import com.sqli.gdmr.Services.MedecinService;
import com.sqli.gdmr.Services.VisiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chargerh")
public class ChargéRHController {

    @Autowired
    private VisiteService visiteService;

    @Autowired
    private CreneauService creneauService;

    @Autowired
    private CollaborateurService collaborateurService;

    @Autowired
    private MedecinService medecinService;

    @Autowired
    private ChargeRHDoctorsMapper doctorsMapper;

    @Autowired
    private ChargeRHCollaborateursMapper collaborateursMapper;

    @Autowired
    private VisiteConfirmationMapper visiteConfirmationMapper;

    @Autowired
    private VisiteChargeRHDashMapper visiteChargeRHDashMapper;

    @Autowired
    private ChargeRHCollabListMapper chargeRHCollabListMapper;

    @Autowired
    private ChargéRHVisitMapper chargéRHVisitMapper;


    @GetMapping("/availableDoctors")
    public ResponseEntity<List<ChargeRHDoctorsDTO>> getAvailableDoctors(){
        List<Medecin> allDoctors = medecinService.findAllDoctors();
        List<Créneau> availableCreneaux = creneauService.findAvailableCreneaux();

        List<ChargeRHDoctorsDTO> doctorsWithAvailability = allDoctors.stream()
                .map(medecin -> {
                    boolean isAvailable = availableCreneaux.stream()
                            .anyMatch(creneau -> creneau.getMedecincren().equals(medecin));
                    return doctorsMapper.toDTO(medecin,isAvailable);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(doctorsWithAvailability);
    }


    @GetMapping("/doctors")
    public ResponseEntity<List<ChargeRHDoctorsDTO>> getAllDoctors() {
        List<Medecin> doctors = medecinService.findAllDoctors();
        List<Créneau> availableCreneaux = creneauService.findAvailableCreneaux();

        List<ChargeRHDoctorsDTO> doctorsDTO = doctorsMapper.toDTOs(doctors, availableCreneaux);

        return ResponseEntity.ok(doctorsDTO);
    }



    @GetMapping("/collaborateurs")
    public ResponseEntity<List<ChargeRHCollabListDTO>> getCollaborateurs(){
        List<Collaborateur> collaborateurs = collaborateurService.findAllCollaborateurs();
        List<ChargeRHCollabListDTO> collaborateursDTO = chargeRHCollabListMapper.toDTOs(collaborateurs);
        return ResponseEntity.ok(collaborateursDTO);
    }


    @GetMapping("/collaborateursdispo")
    public ResponseEntity<List<ChargeRHCollaborateursDTO>> getCollaborateursDispo(){
        List<ChargeRHCollaborateursDTO> collaborateurs = collaborateursMapper.toDTOs(collaborateurService.findCollaborateursWithoutCreneau());
        return ResponseEntity.ok(collaborateurs);
    }

    @PostMapping("/createVisite")
    public ResponseEntity<Visite> createVisite(@RequestParam("creneauId") Long creneauId,
                                               @RequestParam("collaborateurId") Long collaborateurId,
                                               @RequestParam("motif") String motif){
        Visite visite = visiteService.createVisite(creneauId,collaborateurId,motif);
        return ResponseEntity.ok(visite);
    }

    @PostMapping("/assignCreneaux")
    public ResponseEntity<String> assignCreneaux(@RequestParam("medecinId") Long medecinId,
                                                 @RequestParam("chargeRHId") Long chargeRHId,
                                                 @RequestParam("collaborateurId") Long collaborateurId){
        creneauService.assignCreneaux(medecinId, chargeRHId, collaborateurId);
        return ResponseEntity.ok("Available Créneaux assigned successfully.");
    }

    @GetMapping("/visitsToConfirm")
    public ResponseEntity<List<VisiteConfirmationDTO>> getVisitsToConfirm(@RequestParam Long chargéRHId) {
        List<VisiteConfirmationDTO> visits = visiteConfirmationMapper.toDTOs(visiteService.getVisitsToConfirm(chargéRHId));
        return ResponseEntity.ok(visits);
    }

    @PostMapping("/confirmVisit")
    public ResponseEntity<VisiteConfirmationDTO> confirmVisit(@RequestParam Long visiteId,
                                                              @RequestParam String motif,
                                                              @RequestParam TypesVisite typesVisite) {
        VisiteConfirmationDTO visite = visiteConfirmationMapper.toDTO(visiteService.confirmVisit(visiteId,motif,typesVisite));
        return ResponseEntity.ok(visite);
    }

    @DeleteMapping("/cancelVisit")
    public ResponseEntity<Void> cancelVisit(@RequestParam Long visiteId) {
        visiteService.cancelVisit(visiteId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/appointments/today")
    public ResponseEntity<Long> getTotalAppointmentsToday(){
        long count = visiteService.getPlannedAppointmentsForToday();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/appointments/week")
    public ResponseEntity<Long> getTotalAppointmentsWeek(){
        long count = visiteService.getPlannedAppointmentsForWeek();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/appointments/today/list")
    public ResponseEntity<List<VisiteChargeRHDashDTO>> getTodayAppointments(){
        List<Visite> visites = visiteService.getTodayPlannedAppointments();
        List<VisiteChargeRHDashDTO> dtoList = visiteChargeRHDashMapper.toDTOs(visites);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/my-visits")
    public ResponseEntity<List<ChargeRHVisitDTO>> getMyVisits(@RequestParam Long chargéRHId){
        List<Visite> visites = visiteService.getVisitsByChargéRH(chargéRHId);

        List<ChargeRHVisitDTO> visitDTOS = visites.stream()
                .map(visite -> chargéRHVisitMapper.toDTO(visite))
                .collect(Collectors.toList());

        return ResponseEntity.ok(visitDTOS);
    }
}
