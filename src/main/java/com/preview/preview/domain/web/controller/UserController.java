package com.preview.preview.domain.web.controller;

import com.preview.preview.domain.service.user.UserService;
import com.preview.preview.domain.service.user.UserServiceImpl;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.web.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<UserDto> getMyUserInfo(){
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto> getUserInfo(@PathVariable String username){
        return ResponseEntity.ok(userService.getUserWithAuthorities(username));
    }
}