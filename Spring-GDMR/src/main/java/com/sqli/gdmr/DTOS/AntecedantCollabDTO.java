package com.sqli.gdmr.DTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AntecedantCollabDTO {
    private Long idAntecedant;
    private String dateAntecedant;
    private String description;
}
