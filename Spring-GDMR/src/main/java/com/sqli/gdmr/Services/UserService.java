package com.sqli.gdmr.Services;


import com.sqli.gdmr.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long id);

    List<User> getAllUsers();

    User createUser(User user);

    User getCurrentUser();

    void deleteUser(Long id);
}
