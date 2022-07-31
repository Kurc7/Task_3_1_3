package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void saveUser(User user, String[] roles, String pass);
    void removeUser(long id);
    User getUser(long id);
    User getUserByName(String name);
    void updateUser(Long id, String name, String surname, String email, String password, String[] roles);
}
