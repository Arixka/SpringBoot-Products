package com.maria.siverio.apirestproducts.users.dtos;


import com.maria.siverio.apirestproducts.suppliers.models.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class UserRequest {

    @NotNull
    private String username;
    @NotNull
    private String password;


}
