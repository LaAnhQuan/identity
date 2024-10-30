package com.Bai1.identity_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.Bai1.identity_service.dto.request.PermissionRequest;
import com.Bai1.identity_service.dto.response.ApiResponse;
import com.Bai1.identity_service.dto.response.PermissionResponse;
import com.Bai1.identity_service.service.PermissionService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {

        return ApiResponse.<PermissionResponse>builder()
                .code(1000)
                .result(permissionService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .code(1000)
                .result(permissionService.getAll())
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> delete(@PathVariable String permission) {
        permissionService.delete(permission);
        return ApiResponse.<Void>builder().code(1000).build();
    }
}
