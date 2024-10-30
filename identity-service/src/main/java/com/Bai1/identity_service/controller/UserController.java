package com.Bai1.identity_service.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.Bai1.identity_service.dto.request.UserCreationRequest;
import com.Bai1.identity_service.dto.request.UserUpdateRequest;
import com.Bai1.identity_service.dto.response.ApiResponse;
import com.Bai1.identity_service.dto.response.UserResponse;
import com.Bai1.identity_service.service.UserService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    UserService userService;

    @PostMapping
    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        log.info("Controller : create User");
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping()
    ApiResponse<List<UserResponse>> getUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username:{}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUsers())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {

        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {

        return ApiResponse.<UserResponse>builder()
                .result(userService.getMYInfor())
                .build();
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") String userID, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userID, request);
    }

    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return "User has been deleted";
    }
}
