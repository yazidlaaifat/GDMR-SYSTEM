package com.sqli.gdmr.Controller;

import com.sqli.gdmr.DTOS.UserDTO;
import com.sqli.gdmr.Enums.Role;
import com.sqli.gdmr.Mapper.UserMapper;
import com.sqli.gdmr.Models.*;
import com.sqli.gdmr.Services.DossierMedicalService;
import com.sqli.gdmr.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private DossierMedicalService dossierMedicalService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers().stream()
                .filter(user -> !(user instanceof Admin))
                .map(userMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody Map<String, Object> payload){
        String role = (String) payload.get("role");
        User user;
        switch(Role.valueOf(role)){
            case COLLABORATEUR:
                user= new Collaborateur();

                DossierMedical dossierMedical = dossierMedicalService.createDossierMedical();
                ((Collaborateur) user).setDossierMedical(dossierMedical);
                break;
            case CHARGERH:
                user= new ChargéRH();

                break;
            case MEDECIN:
                user= new Medecin();

                break;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
        user.setUsername((String) payload.get("username"));
        user.setPassword((String) payload.get("password"));
        user.setNom((String) payload.get("nom"));
        user.setPrenom((String) payload.get("prenom"));
        user.setRole(Role.valueOf(role));
        user.setDateNaissance(new Date());

        return userService.createUser(user);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
