package com.sqli.gdmr.Mapper;

import com.sqli.gdmr.DTOS.UserDTO;
import com.sqli.gdmr.Models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setIdUser(user.getIdUser());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setRole(user.getRole());
        dto.setDateNaissance(user.getDateNaissance());
        return dto;

    }
}