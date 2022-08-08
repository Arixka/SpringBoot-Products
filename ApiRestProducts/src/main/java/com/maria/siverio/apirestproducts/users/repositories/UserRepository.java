package com.maria.siverio.apirestproducts.users.repositories;

import com.maria.siverio.apirestproducts.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUsersByUsername(String username);
}
