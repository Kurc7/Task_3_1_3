package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("person", userService.getUserByName(principal.getName()));
        model.addAttribute("newUser", new User());
        return "bootstrap";
    }

    @PostMapping(value = "/add")
    public String add(@ModelAttribute("newUser") User user,
                             @RequestParam(value = "userRole", required = false) String[] roles,
                             @ModelAttribute("password") String password) {
        System.out.println(roles.toString());
        userService.saveUser(user, roles, password);
        return "redirect:/admin/";
    }

    @PutMapping(value = "/admin/edit/{id}")
    public String edit(@PathVariable(name = "id") long id, @ModelAttribute(value = "user") User user,
                           @RequestParam(value = "userRole", required = false) String[] roles,
                           @ModelAttribute("password") String password) {
        userService.updateUser(id, user.getName(), user.getLastname(), user.getEmail(), user.getAge(), password, roles);
        return "redirect:/admin/";
    }

    @DeleteMapping(value = "/admin/delete/{id}")
    public String delete (@PathVariable(name = "id") long id) {
        userService.removeUser(id);
        return "redirect:/admin/";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("person", userService.getUserByName(principal.getName()));
        return "bootstrap";
    }
}