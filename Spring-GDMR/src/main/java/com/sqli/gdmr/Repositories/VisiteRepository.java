package com.sqli.gdmr.Repositories;

import com.sqli.gdmr.Enums.StatusVisite;
import com.sqli.gdmr.Models.Visite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisiteRepository extends JpaRepository<Visite,Long> {
    List<Visite> findByCréneau_ChargéRH_IdUserAndMotifIsNullAndStatusVisiteIsNullAndTypesVisiteIsNull(Long chargéRHId);
    List<Visite> findByMedecin_IdUserAndStatusVisite(Long medecinId, StatusVisite status);
    List<Visite> findByStatusVisiteAndDateVisiteBetween(StatusVisite status, LocalDateTime startOfDay, LocalDateTime endOfDay);
    boolean existsByCollaborateur_IdUserAndStatusVisite(Long collaborateurId, StatusVisite status);
    List<Visite> findByCollaborateur_IdUser(Long collaborateurId);

    Optional<Visite> findFirstByCollaborateur_IdUserAndStatusVisite(Long collaborateurId, StatusVisite status);

}
