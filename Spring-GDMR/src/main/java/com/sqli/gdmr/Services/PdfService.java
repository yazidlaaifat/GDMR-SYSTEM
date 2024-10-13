package com.sqli.gdmr.Services;


import com.sqli.gdmr.Models.Antecedant;
import com.sqli.gdmr.Models.DossierMedical;

import java.util.List;

public interface PdfService {
     byte[] generatePdf(DossierMedical dossierMedical, List<Antecedant> antecedants, String collaborateurName, String collaborateurLastName);
    }

