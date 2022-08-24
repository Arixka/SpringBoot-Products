package com.maria.siverio.apirestproducts.users.mappers;

import com.maria.siverio.apirestproducts.users.dtos.RoleDto;
import com.maria.siverio.apirestproducts.users.models.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role dtoRoleToEntity(RoleDto roleDto);

    RoleDto entityRoleToDto(Role role);
}
