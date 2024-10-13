package com.sqli.gdmr.Repositories;


import com.sqli.gdmr.Models.Collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CollaborateurRepository extends JpaRepository<Collaborateur,Long> {

}
