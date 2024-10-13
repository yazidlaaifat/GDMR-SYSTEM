package com.sqli.gdmr.Controller;

import com.sqli.gdmr.DTOS.CollabVisitDTO;
import com.sqli.gdmr.DTOS.CollaborateurProfileDTO;
import com.sqli.gdmr.DTOS.CreneauCollaborateurDTO;
import com.sqli.gdmr.Mapper.CollabVisitMapper;
import com.sqli.gdmr.Mapper.CollaborateurProfileMapper;
import com.sqli.gdmr.Mapper.CreneauCollaborateurMapper;
import com.sqli.gdmr.Models.Collaborateur;
import com.sqli.gdmr.Models.Créneau;
import com.sqli.gdmr.Models.DossierMedical;
import com.sqli.gdmr.Models.Visite;
import com.sqli.gdmr.Services.CollaborateurService;
import com.sqli.gdmr.Services.CreneauService;
import com.sqli.gdmr.Services.VisiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("collaborateur")
public class CollaborateurController {

    @Autowired
    private VisiteService visiteService;

    @Autowired
    private CreneauService creneauService;

    @Autowired
    private CreneauCollaborateurMapper creneauCollaborateurMapper;

    @Autowired
    private CollaborateurService collaborateurService;

    @Autowired
    private CollaborateurProfileMapper collaborateurProfileMapper;

    @Autowired
    private CollabVisitMapper collabVisitMapper;


    @GetMapping("/availableCreneaux/{collaborateurId}")
    public ResponseEntity<List<CreneauCollaborateurDTO>> getAvailableCreneaux(@PathVariable Long collaborateurId) {
        List<Créneau> creneaux = creneauService.getCreneauxByCollaborateurId(collaborateurId);
        List<CreneauCollaborateurDTO> dtoList = creneauCollaborateurMapper.toDTOList(creneaux);
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("/confirmCreneau")
    public ResponseEntity<Visite> confirmCreneau(@RequestParam Long creneauId,
                                                 @RequestParam Long collaborateurId){
        Visite visite = visiteService.createVisiteFromCreneau(creneauId,collaborateurId);
        creneauService.releaseUnchosenCreneaux(creneauId,collaborateurId);
        return ResponseEntity.ok(visite);
    }

    @GetMapping("/checkDossier/{collaborateurId}")
    public ResponseEntity<Boolean> isDossierComplete(@PathVariable Long collaborateurId){
        boolean isComplete = creneauService.isDossierMedicalComplete(collaborateurId);
        return ResponseEntity.ok(isComplete);
    }

    @GetMapping("/profile/{collaborateurId}")
    public ResponseEntity<CollaborateurProfileDTO> getProfile(@PathVariable Long collaborateurId){
        Collaborateur collaborateur = collaborateurService.findById(collaborateurId);
        CollaborateurProfileDTO profileDTO = collaborateurProfileMapper.toDTO(collaborateur);
        return ResponseEntity.ok(profileDTO);
    }

    @PostMapping("/updateProfile")
    public ResponseEntity<CollaborateurProfileDTO> updateProfile(@RequestParam Long collaborateurId,
                                                                 @RequestBody CollaborateurProfileDTO profileDTO){

        DossierMedical updatedDossier = new DossierMedical();
        updatedDossier.setSexe(profileDTO.getDossierMedical().getSexe());
        updatedDossier.setHeight(profileDTO.getDossierMedical().getHeight());
        updatedDossier.setWeight(profileDTO.getDossierMedical().getWeight());
        updatedDossier.setGroupeSanguin(profileDTO.getDossierMedical().getGroupeSanguin());
        updatedDossier.setAllergies(profileDTO.getDossierMedical().getAllergies());
        updatedDossier.setMedicalHistory(profileDTO.getDossierMedical().getMedicalHistory());
        updatedDossier.setMedications(profileDTO.getDossierMedical().getMedications());


        Collaborateur updatedCollaborateur = collaborateurService.updateProfile(collaborateurId, profileDTO.getDepartement(),updatedDossier);

        CollaborateurProfileDTO updatedProfileDTO = collaborateurProfileMapper.toDTO(updatedCollaborateur);
        return ResponseEntity.ok(updatedProfileDTO);
    }

    @GetMapping("/visites/{collaborateurId}")
    public ResponseEntity<List<CollabVisitDTO>> getVisitesByCollaborateurId(@PathVariable Long collaborateurId){
        List<Visite> visites = visiteService.getVisitesByCollaborateurId(collaborateurId);
        List<CollabVisitDTO> visitDTOs = visites.stream()
                                                .map(collabVisitMapper::toDTO)
                                                .collect(Collectors.toList());
        return ResponseEntity.ok(visitDTOs);
    }

}
