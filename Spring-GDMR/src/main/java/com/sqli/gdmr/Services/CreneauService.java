package com.sqli.gdmr.Services;

import com.sqli.gdmr.Models.Créneau;
import com.sqli.gdmr.Models.Medecin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CreneauService {

    List<Créneau> parseCSV(MultipartFile file, Medecin medecin) throws IOException;

    void saveCreneaux(List<Créneau> creneaux);

    void assignCreneaux(Long creneauId, Long chargeRHId, Long collaborateurId);

    void releaseUnchosenCreneaux(Long chosenCreneauId, Long collaborateurId);

    List<Créneau> getCreneauxByCollaborateurId(Long collaborateurId);

    Créneau getCreneauById(Long creneauId);

    void saveCreneau(Créneau créneau);

    List<Créneau> findAvailableCreneaux();

    boolean isDossierMedicalComplete(Long collaborateurId);
}
