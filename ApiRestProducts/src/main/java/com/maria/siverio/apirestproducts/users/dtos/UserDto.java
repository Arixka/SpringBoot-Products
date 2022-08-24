package com.maria.siverio.apirestproducts.users.dtos;

import com.maria.siverio.apirestproducts.users.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotNull(message = "Username may not be empty")
    private String username;
    private List<RoleDto> roles;
}
