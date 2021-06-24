package com.github.carloshh.poc.api;

import com.github.carloshh.poc.api.dto.UserRequest;
import com.github.carloshh.poc.domain.User;
import com.github.carloshh.poc.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest.username(), userRequest.email());
    }

}
