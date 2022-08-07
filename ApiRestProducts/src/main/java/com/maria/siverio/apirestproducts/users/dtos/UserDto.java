package com.maria.siverio.apirestproducts.users.dtos;

import com.maria.siverio.apirestproducts.users.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotEmpty(message = "Username may not be empty")
    private String username;

    private List<Role> roles;
}
