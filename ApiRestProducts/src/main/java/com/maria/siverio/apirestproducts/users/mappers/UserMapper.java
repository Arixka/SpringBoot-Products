package com.maria.siverio.apirestproducts.users.mappers;

import com.maria.siverio.apirestproducts.users.dtos.RoleDto;
import com.maria.siverio.apirestproducts.users.models.Role;
import com.maria.siverio.apirestproducts.users.models.User;
import com.maria.siverio.apirestproducts.users.dtos.UserDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User dtoToEntity(UserDto userDto);
    UserDto entityToDTO(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UserDto userDto, @MappingTarget User user);
}
