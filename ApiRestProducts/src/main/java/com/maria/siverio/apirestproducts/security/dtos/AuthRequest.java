package com.maria.siverio.apirestproducts.security.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest  implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;

    private String username;
    private String password;



}
