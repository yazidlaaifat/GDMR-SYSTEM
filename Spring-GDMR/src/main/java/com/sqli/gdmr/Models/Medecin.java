package com.sqli.gdmr.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("MEDECIN")
@Data
public class Medecin extends User {
    private String SiteTravail;
    private String Specialite;
    private String qualification;
    private String experience;

    @OneToMany(mappedBy = "medecin", fetch = FetchType.LAZY)
    private List<Visite> visites=new ArrayList<>();

    @OneToMany(mappedBy = "medecincren", fetch = FetchType.LAZY)
    private List<Créneau> créneaus=new ArrayList<>();
}
