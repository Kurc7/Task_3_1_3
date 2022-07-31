package ru.kata.spring.boot_security.demo.Dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void saveUser(User user);
    void removeUser(long id);
    User getUser(long id);
    void updateUser(User user);
    User getUserByName(String name);
}
