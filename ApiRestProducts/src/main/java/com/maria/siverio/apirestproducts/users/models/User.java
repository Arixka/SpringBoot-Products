package com.maria.siverio.apirestproducts.users.models;

import com.maria.siverio.apirestproducts.users.dtos.RoleDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "id_user")
    private Long idUser;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    //roles
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_role"
            ))
    private List<Role> roles= new ArrayList<>();
    public void addRole(Role role) {
        roles.add(role);
    }

}
