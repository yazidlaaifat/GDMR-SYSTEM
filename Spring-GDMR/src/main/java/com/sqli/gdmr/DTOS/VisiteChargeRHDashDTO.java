package com.sqli.gdmr.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VisiteChargeRHDashDTO {
    private String time;
    private String patientName;
    private String doctorName;
    private String motif;
}
