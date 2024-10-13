package com.sqli.gdmr.Repositories;


import com.sqli.gdmr.Models.Antecedant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AntecedantRepository extends JpaRepository<Antecedant,Long> {
    List<Antecedant> findByDossierMedical_IdDossierMedical(Long dossierMedicalId);

}
