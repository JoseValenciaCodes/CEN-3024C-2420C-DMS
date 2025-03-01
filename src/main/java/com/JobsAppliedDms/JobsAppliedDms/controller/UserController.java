package com.JobsAppliedDms.JobsAppliedDms.controller;


import com.JobsAppliedDms.JobsAppliedDms.dto.LoginDto;
import com.JobsAppliedDms.JobsAppliedDms.dto.UserDto;
import com.JobsAppliedDms.JobsAppliedDms.payload.MessagePayload;
import com.JobsAppliedDms.JobsAppliedDms.payload.UserPayload;
import com.JobsAppliedDms.JobsAppliedDms.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
* User Controller
* Handle HTTP Interactions related with the User Entity
* */

@RestController
@RequestMapping("/api/auth")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* Make a POST Request to Register */
    @PostMapping("/register")
    public ResponseEntity<UserPayload> registerUser(@Validated @RequestBody UserDto userDto, HttpSession httpSession)
    {
        UserPayload userPayload = userService.registerUser(userDto, httpSession);
        return ResponseEntity.ok(userPayload);
    }

    /* Make a POST Request to Login */
    @PostMapping("/login")
    public ResponseEntity<UserPayload> loginUser(@Validated @RequestBody LoginDto loginDto, HttpSession httpSession)
    {
        UserPayload userPayload = userService.loginUser(loginDto, httpSession);
        return ResponseEntity.ok(userPayload);
    }

    /* Make a POST Request to Logout */
    @PostMapping("/logout")
    public ResponseEntity<MessagePayload> logoutUser(HttpSession httpSession)
    {
        MessagePayload messagePayload = userService.logout(httpSession);
        return ResponseEntity.ok(messagePayload);
    }
}
