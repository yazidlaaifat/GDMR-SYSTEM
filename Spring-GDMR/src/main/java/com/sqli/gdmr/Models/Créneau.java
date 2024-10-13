package com.sqli.gdmr.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Créneau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCréneau;
    private boolean disponibilité;
    private Date dateDebut;
    private Date dateFin;

    @ManyToOne
    private ChargéRH chargéRH;

    @ManyToOne
    private Medecin medecincren;

    @ManyToOne
    private Collaborateur collaborateurcren;
}
