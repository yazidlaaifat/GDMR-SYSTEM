package com.sqli.gdmr.Models;

import com.sqli.gdmr.Enums.StatusVisite;
import com.sqli.gdmr.Enums.TypesVisite;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Visite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdVisite;
    private String motif;

    @Enumerated(EnumType.STRING)
    private StatusVisite statusVisite;

    @Enumerated(EnumType.STRING)
    private TypesVisite typesVisite;

    private Date dateVisite;

    @ManyToOne
    private Collaborateur collaborateur;

    @ManyToOne
    private Medecin medecin;

    @OneToOne
    @JoinColumn(name = "creneau_id")
    private Créneau créneau;

}
