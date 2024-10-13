package com.sqli.gdmr.Repositories;


import com.sqli.gdmr.Models.Créneau;
import com.sqli.gdmr.Models.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CréneauRepository extends JpaRepository<Créneau,Long> {

    boolean existsByDateDebutAndDateFinAndMedecincren(Date dateDebut, Date dateFin, Medecin medecincren);
    List<Créneau> findByDisponibilitéTrue();
    List<Créneau> findByMedecincrenAndDisponibilitéTrue(Medecin medecinId);
    List<Créneau> findByCollaborateurcren_IdUser(Long collaborateurId);
    List<Créneau> findByCollaborateurcren_IdUserAndIdCréneauNot(Long collaborateurId, Long idNot);

}
