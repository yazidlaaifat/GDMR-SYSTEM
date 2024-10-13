package com.sqli.gdmr.Services;

import com.sqli.gdmr.Exceptions.ResourceNotFoundException;
import com.sqli.gdmr.Models.Medecin;
import com.sqli.gdmr.Repositories.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedecinServiceImplementation implements MedecinService{
    @Autowired
    private MedecinRepository medecinRepository;

    @Override
    public Medecin updateMedecin(Long id, Medecin medecinDetails) {
        Medecin medecin = medecinRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Medecin not found for this id :: " + id));

        medecin.setSiteTravail(medecinDetails.getSiteTravail());
        medecin.setSpecialite(medecinDetails.getSpecialite());
        medecin.setQualification(medecinDetails.getQualification());
        medecin.setExperience(medecinDetails.getExperience());

        return medecinRepository.save(medecin);
    }

    @Override
    public Optional<Medecin> getMedecinById(Long id) {
        return medecinRepository.findById(id);
    }

    @Override
    public List<Medecin> findAllDoctors() {
        return medecinRepository.findAll();
    }
}
