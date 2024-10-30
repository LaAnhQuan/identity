package com.Bai1.identity_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.Bai1.identity_service.dto.request.RoleRequest;
import com.Bai1.identity_service.dto.response.ApiResponse;
import com.Bai1.identity_service.dto.response.RoleResponse;
import com.Bai1.identity_service.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {

        return ApiResponse.<RoleResponse>builder()
                .code(1000)
                .result(roleService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .code(1000)
                .result(roleService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role) {
        roleService.delete(role);
        return ApiResponse.<Void>builder().code(1000).build();
    }
}
