package com.sqli.gdmr.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class DossierMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDossierMedical;
    private String Sexe;
    private int Height;
    private int Weight;
    private String GroupeSanguin;
    private String Allergies;
    private String medicalHistory;
    private String medications;
    @OneToMany(mappedBy = "dossierMedical", fetch = FetchType.LAZY)
    private List<Antecedant> antecedants;

    @OneToOne(mappedBy = "dossierMedical")
    private Collaborateur collaborateur;

}
