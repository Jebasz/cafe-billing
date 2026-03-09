package com.star.cafe_billing.controller;


import com.star.cafe_billing.entity.User;
import com.star.cafe_billing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){

        return userService.createUser(user);

    }

    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody User user){

        return userService.updateUser(id,user);

    }

    @GetMapping("/shop/{shopId}")
    public List<User> getUsers(@PathVariable Long shopId){

        return userService.getUsersByShop(shopId);

    }

    @DeleteMapping("/{id}")
    public void disableUser(@PathVariable Long id){

        userService.disableUser(id);

    }

}