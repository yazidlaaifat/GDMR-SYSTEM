package com.sqli.gdmr.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("COLLABORATEUR")
@Data
public class Collaborateur extends User {
    private String Departement;
    @OneToOne
    @JoinColumn(name = "dossierMedical_Id")
    private DossierMedical dossierMedical;
    @OneToMany(mappedBy = "collaborateur", fetch = FetchType.LAZY)
    private List<Visite> visites=new ArrayList<>();

    @OneToMany(mappedBy = "collaborateurcren", fetch = FetchType.LAZY)
    private List<Créneau> créneaus=new ArrayList<>();

}
