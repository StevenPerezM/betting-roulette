package com.masivian.casino.controllers;

import com.masivian.casino.dto.RouletteDto;
import com.masivian.casino.models.Roulette;
import com.masivian.casino.models.User;
import com.masivian.casino.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {
    private final UserService userService;

    @GetMapping()
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            users = userService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user){
        try {
            User newUser = userService.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
