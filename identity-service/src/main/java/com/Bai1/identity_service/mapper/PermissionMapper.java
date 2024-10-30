package com.Bai1.identity_service.mapper;

import org.mapstruct.Mapper;

import com.Bai1.identity_service.dto.request.PermissionRequest;
import com.Bai1.identity_service.dto.response.PermissionResponse;
import com.Bai1.identity_service.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionRespone(Permission permission);
}
