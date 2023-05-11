package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MainController {
    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/user")
    public String homePage(Principal principal, Model model) {
        System.out.println(principal.getName());
        model.addAttribute("user", userService.findByUsername(principal.getName()));

        return "user";
    }

    @GetMapping(value = "/admin")
    public String printUsers(Model model) {
        model.addAttribute("users", userService.listUser());
        return "users";
    }

    @DeleteMapping(value = "/admin/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/add_user")
    public String addUser(@RequestParam(name = "name") String name,
                          @RequestParam(name = "surname") String surname,
                          @RequestParam(name = "age") Integer age,
                          @RequestParam(name = "password") String password,
                          @RequestParam(name = "roles") String nameRole) throws UnsupportedEncodingException {
        System.out.println(name);
        Set<Role> roles = new HashSet<>();
        Role role = new Role(nameRole);
        roles.add(role);
        User user = new User(name, surname, age, password, roles);
        role.setUser(user);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/{id}/edit")
    public String edit(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.findUser(id);
        System.out.println(user.toString());
        model.addAttribute("user", user);
        return "edit";
    }

    @PatchMapping(value = "/admin/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable Long id){
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/")
    public String printContact(){
        return "contacts";
    }
}
