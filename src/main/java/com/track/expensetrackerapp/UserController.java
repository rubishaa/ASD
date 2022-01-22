package com.track.expensetrackerapp.controller;

import com.track.expensetrackerapp.dto.UserDto;
import com.track.expensetrackerapp.model.User;
import com.track.expensetrackerapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    ResponseEntity<User> findUserById(@RequestParam Integer id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    ResponseEntity<User> validateUser(@RequestBody UserDto user) {
        Boolean aBoolean = userService.validateUser(user);
        if (aBoolean) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
