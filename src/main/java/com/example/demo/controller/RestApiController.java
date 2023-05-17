package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class RestApiController {

    private final UserService userService;

    @Autowired
    public RestApiController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/admin")
    public List<User> printUsers() {
        return userService.listUser();
    }

    @PostMapping(value = "/admin/add_user")
    public User addUser(@RequestBody User user) {
        userService.saveUser(user);
        return userService.saveUser(user);
    }

    @DeleteMapping(value = "/admin/{id}")
    public User deleteUser(@PathVariable(name = "id") Long id) {
       userService.deleteUser(id);
       return userService.findUser(id);
    }

    @PatchMapping(value = "/admin/{id}")
    public User update(@RequestBody User user, @PathVariable Long id){
    //    System.out.println(user.getRoles());
        return userService.updateUser(id, user);
    }


}
