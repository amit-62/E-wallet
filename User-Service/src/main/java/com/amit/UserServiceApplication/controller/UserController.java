package com.amit.UserServiceApplication.controller;

import com.amit.UserServiceApplication.dtos.UserRequestDTO;
import com.amit.UserServiceApplication.models.Users;
import com.amit.UserServiceApplication.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.json.JSONException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

@RestController
@RequestMapping("/user")
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/addUpdate")
    private ResponseEntity<Users> addUser(@RequestBody @Valid UserRequestDTO dto) throws JSONException, JsonProcessingException {
        Users user = userService.addUpdate(dto);

        if(user != null){
            ResponseEntity response = new ResponseEntity(user, HttpStatus.OK);
            return response;
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }
}
