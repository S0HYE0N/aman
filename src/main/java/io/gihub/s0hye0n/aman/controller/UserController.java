package io.gihub.s0hye0n.aman.controller;

import io.gihub.s0hye0n.aman.dto.CreateUserRequest;
import io.gihub.s0hye0n.aman.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 로그인 유저 추가
    @PostMapping("/api/users")
    public ResponseEntity<Long> createUser(@Valid @RequestBody CreateUserRequest request) {

        Long id = userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

}
