package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserDao userDao, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    @Override
    public void saveUser(User user, String[] roles, String password) {
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Arrays.stream(roles).map(roleService::findByName).collect(Collectors.toList()));
        userDao.saveUser(user);
    }

    @Transactional
    @Override
    public void removeUser(long id) {
        userDao.removeUser(id);
    }

    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Transactional
    @Override
    public void updateUser(Long id, String name, String lastName, String email, int age, String password, String[] roles) {
        User user = getUser(id);
        if (password != null && password.length() > 0) {
            user.setPassword(passwordEncoder.encode(password));
        }
        if (roles != null && roles.length > 0) {
            user.setRoles(Arrays.stream(roles).map(roleService::findByName).collect(Collectors.toList()));
        }
        user.setAge(age);
        user.setName(name);
        user.setLastname(lastName);
        user.setEmail(email);
        userDao.updateUser(user);
    }
}
