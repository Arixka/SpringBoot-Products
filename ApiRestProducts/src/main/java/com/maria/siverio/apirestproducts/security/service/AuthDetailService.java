package com.maria.siverio.apirestproducts.security.service;


import com.maria.siverio.apirestproducts.security.util.EncryptionUtil;
import com.maria.siverio.apirestproducts.users.dtos.UserDto;
import com.maria.siverio.apirestproducts.users.dtos.UserRequest;
import com.maria.siverio.apirestproducts.users.mappers.UserMapper;
import com.maria.siverio.apirestproducts.users.models.Role;
import com.maria.siverio.apirestproducts.users.models.User;
import com.maria.siverio.apirestproducts.users.repositories.RoleRepository;
import com.maria.siverio.apirestproducts.users.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthDetailService implements UserDetailsService {
    //TODO cambiar nombre a UserTedailsSrviceImpl

    private static final Logger logger = LoggerFactory.getLogger(AuthDetailService.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUsersByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        //TODO ERROR CON LOS ROLES PQ NO LOS GUARDAMOS
        UserDetailsImpl userDetails;
        userDetails = UserDetailsImpl.build(user);
        return userDetails;
    }

    //metodo para crear usuario con encriptacion de contraseña

//    public UserDto addUser(UserRequest userData) {
//        logger.debug("addUser {}", userData);
//        User newUser = new User();
//        newUser.setUsername(userData.getUsername());
//        newUser.setPassword(EncryptionUtil.encrypt(userData.getPassword()));
//        //TODO ERROR AL AÑADIR ROLE
//        Set<Role> roles = new HashSet<>();
//        roles.add(roleRepository.getRoleByName("USER"));
//        newUser.setRoles(roles);
//
//        return  userMapper.entityToDTO(userRepository.save(newUser));
//    }

}
