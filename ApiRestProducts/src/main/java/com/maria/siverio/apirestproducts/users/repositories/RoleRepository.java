package com.maria.siverio.apirestproducts.users.repositories;

import com.maria.siverio.apirestproducts.users.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long> {
    Role getRoleByName(String name);
}
