package com.sqli.gdmr.Controller;


import com.sqli.gdmr.DTOS.UserDTO;
import com.sqli.gdmr.Mapper.UserMapper;
import com.sqli.gdmr.Models.User;
import com.sqli.gdmr.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> ResponseEntity.ok(userMapper.toUserDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserDTO> getCurrentUser(){
        User currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(userMapper.toUserDTO(currentUser));
    }
}
