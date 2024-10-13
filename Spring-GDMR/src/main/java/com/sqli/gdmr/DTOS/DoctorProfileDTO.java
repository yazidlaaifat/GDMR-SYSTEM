package com.sqli.gdmr.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorProfileDTO {
    private String username;
    private String role;
    private String siteTravail;
    private String specialite;
    private String qualification;
    private String experience;
}
