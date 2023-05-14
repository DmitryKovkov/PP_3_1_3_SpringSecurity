package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
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
    private final RoleService roleService;

    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
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
        model.addAttribute("listRoles", roleService.listRole());
        return "users";
    }

    @DeleteMapping(value = "/admin/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/add_user")
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/{id}/edit")
    public String edit(@PathVariable(name = "id") Long id, Model model) {
        User user = userService.findUser(id);
      //  System.out.println(user.toString());
        model.addAttribute("user", user);
        return "edit";
    }

    @PatchMapping(value = "/admin/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable Long id){
        System.out.println(user.getRoles());
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/")
    public String printContact(){
        return "contacts";
    }

    @GetMapping(value = "/admin/add_user")
    public String addUser(Model model) {
        model.addAttribute("listRole", roleService.listRole());
        return "add_user";
    }
}
