package com.maria.siverio.apirestproducts.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
public class AuthResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private Long id;
    private String username;
    private List<String> roles;
    private final String jwtToken;


}
