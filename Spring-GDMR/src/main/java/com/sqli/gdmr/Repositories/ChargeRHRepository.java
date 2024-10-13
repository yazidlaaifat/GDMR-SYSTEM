package com.sqli.gdmr.Repositories;



import com.sqli.gdmr.Models.ChargéRH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeRHRepository extends JpaRepository<ChargéRH,Long> {

}
