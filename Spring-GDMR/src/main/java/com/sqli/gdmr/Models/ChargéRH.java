package com.sqli.gdmr.Models;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@DiscriminatorValue("CHARGERH")
public class ChargéRH extends User {
    @OneToMany(mappedBy = "chargéRH", fetch = FetchType.LAZY)
    private List<Créneau> créneaus=new ArrayList<>();
}
