package com.maria.siverio.apirestproducts.users.mappers;

import com.maria.siverio.apirestproducts.users.User;
import com.maria.siverio.apirestproducts.users.dtos.UserDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {


    User dtoToEntity(UserDto userDto);

    UserDto entityToDTO(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UserDto userDto, @MappingTarget User user);
}
