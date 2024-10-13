package com.sqli.gdmr.Repositories;


import com.sqli.gdmr.Models.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DossierMedicalRepository extends JpaRepository<DossierMedical,Long> {
    DossierMedical findByCollaborateur_IdUser(Long collaborateurId);
    Optional<Long> findCollaborateur_IdUserByIdDossierMedical(Long idDossierMedical);
}
