package com.sqli.gdmr.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Antecedant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAntecedant;
    private Date dateAntecedant;
    private String description;
    @ManyToOne
    @JoinColumn(name = "dossierMedical_id")
    private DossierMedical dossierMedical;
}
