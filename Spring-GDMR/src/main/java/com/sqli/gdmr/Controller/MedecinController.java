package com.sqli.gdmr.Controller;

import com.sqli.gdmr.DTOS.AntecedantDTO;
import com.sqli.gdmr.DTOS.DoctorProfileDTO;
import com.sqli.gdmr.DTOS.DossierMedicalDTO;
import com.sqli.gdmr.DTOS.PageVisitesActivesDTO;
import com.sqli.gdmr.Enums.StatusVisite;
import com.sqli.gdmr.Exceptions.ResourceNotFoundException;
import com.sqli.gdmr.Mapper.AntecedantMapper;
import com.sqli.gdmr.Mapper.DoctorProfileMapper;
import com.sqli.gdmr.Mapper.DossierMedicalMapper;
import com.sqli.gdmr.Mapper.PageVisitesActivesMapper;
import com.sqli.gdmr.Models.*;
import com.sqli.gdmr.Repositories.CréneauRepository;
import com.sqli.gdmr.Repositories.VisiteRepository;
import com.sqli.gdmr.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medecin")
public class MedecinController {

    @Autowired
    private VisiteService visiteserv;

    @Autowired
    private PageVisitesActivesMapper pageVisitesActivesMapper=new PageVisitesActivesMapper();

    @Autowired
    private AntecedantService antecedantService;

    @Autowired
    private AntecedantMapper antecedantMapper=new AntecedantMapper();

    @Autowired
    private PdfService pdfService;

    @Autowired
    private DossierMedicalService dossierMedicalService;

    @Autowired
    private DossierMedicalMapper dossierMedicalMapper = new DossierMedicalMapper();

    @Autowired
    private MedecinService medecinService;

    @Autowired
    private CreneauService creneauService;

    @Autowired
    private DoctorProfileMapper doctorProfileMapper;

    //bad practice
    @Autowired
    private CréneauRepository créneauRepository;

    @Autowired
    private VisiteRepository visiteRepository;


    @GetMapping("/ListVisitesCurrent")
    public ResponseEntity<List<PageVisitesActivesDTO>> MesVisitesEnCours(@RequestParam Long medecinId)
    {
        List<Visite> visiteList=visiteserv.getVisitsByMedecinAndStatus(medecinId,StatusVisite.Planifié);
        List<PageVisitesActivesDTO> visitesEnCourList= visiteList.stream()
                .map(pageVisitesActivesMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(visitesEnCourList);
    }

    @GetMapping("/plannedVisitsCount")
    public ResponseEntity<Long> getPlannedVisitsCountForMedecin(@RequestParam Long medecinId) {
        List<Visite> plannedVisits = visiteserv.getVisitsByMedecinAndStatus(medecinId, StatusVisite.Planifié);
        return ResponseEntity.ok((long) plannedVisits.size());
    }

    @GetMapping("/ListVisitesPast")
    public ResponseEntity<List<PageVisitesActivesDTO>> getPastVisits(@RequestParam Long medecinId) {
        List<Visite> pastVisits = visiteserv.getVisitsByMedecinAndStatus(medecinId, StatusVisite.Passé);
        List<PageVisitesActivesDTO> pastVisitsDTO = pastVisits.stream()
                .map(pageVisitesActivesMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pastVisitsDTO);
    }


    @GetMapping("/antecedant/{dossierMedicalId}")
    public ResponseEntity<List<AntecedantDTO>> getAntecedantsByDossierMedical(@PathVariable Long dossierMedicalId) {
        List<Antecedant> antecedantsList=antecedantService.getAntecedantsByDossierMedical(dossierMedicalId);
        List<AntecedantDTO> actualAntecedants=new ArrayList<>();
        for(Antecedant a:antecedantsList){
            actualAntecedants.add(antecedantMapper.toDTO(a));
        }
        return ResponseEntity.ok(actualAntecedants);
    }

    //
    @PostMapping("/antecedants")
    public ResponseEntity<Antecedant> addAntecedant(@RequestBody AntecedantDTO antecedant) {
        Antecedant antecedant1 = antecedantService.addAntecedant(antecedantMapper.reverseDTO(antecedant));

        Long collaborateurId = dossierMedicalService.getCollaborateurIdByDossierMedicalId(antecedant.getDossierMedicalId());
        Visite visite = visiteserv.findByCollaborateurIdAndStatus(collaborateurId, StatusVisite.Planifié);

        if (visite != null) {
            visite.setStatusVisite(StatusVisite.Passé);
            visiteserv.updateVisite(visite);
        }
        return ResponseEntity.ok(antecedant1);
    }


    @GetMapping("/generate-pdf/{collaborateurId}/{collaborateurName}/{collaborateurLastName}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long collaborateurId,@PathVariable String collaborateurName,@PathVariable String collaborateurLastName) {
        DossierMedical dossierMedical = dossierMedicalService.getDossierMedicalByCollaborateurId(collaborateurId);

        List<Antecedant> antecedants = antecedantService.getAntecedantsByDossierMedical(dossierMedical.getIdDossierMedical());

        byte[] pdfBytes = pdfService.generatePdf(dossierMedical, antecedants, collaborateurName, collaborateurLastName);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=visitor-profile.pdf");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

    @GetMapping("/dossierMedical/{collaborateurId}")
    public ResponseEntity<DossierMedicalDTO> getDossierMedicalByCollaborateurId(@PathVariable Long collaborateurId) {
        return ResponseEntity.ok(dossierMedicalMapper.toDTO(dossierMedicalService.getDossierMedicalByCollaborateurId(collaborateurId)));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Medecin> updateMedecin(@PathVariable(value = "id") Long id, @RequestBody Medecin medecinDetails) {
        Medecin updatedMedecin = medecinService.updateMedecin(id, medecinDetails);
        return ResponseEntity.ok(updatedMedecin);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorProfileDTO> getMedecinById(@PathVariable(value = "id") Long id){
        Medecin medecin = medecinService.getMedecinById(id).orElseThrow(() -> new ResourceNotFoundException("Medecin not found for this id :: " + id));

        DoctorProfileDTO doctorProfileDTO = doctorProfileMapper.toDTO(medecin);
        return ResponseEntity.ok().body(doctorProfileDTO);
    }


    @PostMapping("/uploadPlanning")
    public ResponseEntity<String> uploadPlanning(@RequestParam("file")MultipartFile file,@RequestParam("medecinId") Long medecinId){
        Medecin medecin = medecinService.getMedecinById(medecinId)
                .orElseThrow(() -> new ResourceNotFoundException("Medecin not found"));
        try {
            List<Créneau> creneaux = creneauService.parseCSV(file,medecin);
            creneauService.saveCreneaux(creneaux);
            return ResponseEntity.ok("Planning uploaded successfully.");
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing file.");
        }
    }

    @PutMapping("/end/{id}")
    public ResponseEntity<?> endVisit(@PathVariable Long id) {
        return visiteRepository.findById(id).map(visite -> {
            if (visite.getStatusVisite() == StatusVisite.Planifié) {
                visite.setStatusVisite(StatusVisite.Passé);
                visiteRepository.save(visite);

                if (visite.getCréneau() != null) {
                    créneauRepository.deleteById(visite.getCréneau().getIdCréneau());
                }

                return ResponseEntity.ok("Visit ended successfully");
            } else {
                return ResponseEntity.badRequest().body("Visit is not in a state that can be ended");
            }
        }).orElse(ResponseEntity.notFound().build());
    }

}
