package com.Bai1.identity_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Bai1.identity_service.dto.request.PermissionRequest;
import com.Bai1.identity_service.dto.response.PermissionResponse;
import com.Bai1.identity_service.entity.Permission;
import com.Bai1.identity_service.mapper.PermissionMapper;
import com.Bai1.identity_service.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        permission = permissionRepository.save(permission);
        return permissionMapper.toPermissionRespone(permission);
    }

    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream().map(permissionMapper::toPermissionRespone).toList();
    }

    public void delete(String permission) {
        permissionRepository.deleteById(permission);
    }
}
