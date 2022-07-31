package ru.kata.spring.boot_security.demo.Dao;

import ru.kata.spring.boot_security.demo.model.Role;
import java.util.List;

public interface RoleDao {
    Role findByName (String role);
    void save (Role role);
    List<Role> getAllRoles();
}
