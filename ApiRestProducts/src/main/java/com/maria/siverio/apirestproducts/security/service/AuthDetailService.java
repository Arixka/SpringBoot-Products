package com.maria.siverio.apirestproducts.security.service;


import com.maria.siverio.apirestproducts.users.dtos.UserDto;
import com.maria.siverio.apirestproducts.users.dtos.UserRequest;
import com.maria.siverio.apirestproducts.users.mappers.UserMapper;
import com.maria.siverio.apirestproducts.users.models.Role;
import com.maria.siverio.apirestproducts.users.models.User;
import com.maria.siverio.apirestproducts.users.repositories.RoleRepository;
import com.maria.siverio.apirestproducts.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUsersByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        //TODO ERROR CON LOS ROLES PQ NO LOS GUARDAMOS
//        List<Role> roles = user.getRoles();
//        Set<SimpleGrantedAuthority> authorities = new HashSet<>(1);
//        authorities.add(new SimpleGrantedAuthority(user.getRoles().get(0).getName()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    //metodo para crear usuario con encriptacion de contraseña

    public UserDto addUser(UserRequest userData) throws Exception{
        User newUser = new User();
        newUser.setUsername(userData.getUsername());
        newUser.setPassword(new BCryptPasswordEncoder().encode(userData.getPassword()));
        // al crearlos siempre seran user, ya luego podemos crear un enpoint para crear admin
//        newUser.addRole(roleRepository.getRoleByName("USER")); //TODO ERROR AL AÑADIR ROLE
        return userMapper.entityToDTO(userRepository.save(newUser));
    }

}
