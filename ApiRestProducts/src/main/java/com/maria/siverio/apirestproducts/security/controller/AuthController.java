package com.maria.siverio.apirestproducts.security.controller;


import com.maria.siverio.apirestproducts.security.dtos.AuthRequest;
import com.maria.siverio.apirestproducts.security.dtos.AuthResponse;
import com.maria.siverio.apirestproducts.security.service.AuthDetailService;
import com.maria.siverio.apirestproducts.security.service.UserDetailsImpl;
import com.maria.siverio.apirestproducts.security.util.EncryptionUtil;
import com.maria.siverio.apirestproducts.security.util.JwtTokenUtil;
import com.maria.siverio.apirestproducts.users.dtos.UserRequest;
import com.maria.siverio.apirestproducts.users.mappers.UserMapper;
import com.maria.siverio.apirestproducts.users.models.Role;
import com.maria.siverio.apirestproducts.users.models.User;
import com.maria.siverio.apirestproducts.users.repositories.RoleRepository;
import com.maria.siverio.apirestproducts.users.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthDetailService authDetailService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> createAuthenticationToken(@Valid @RequestBody AuthRequest authRequest) throws Exception {
// al primer login cambiar la pass?
        authenticate(authRequest.getUsername(), authRequest.getPassword());
//TODO ERROR no pasa de aqui
        UserDetailsImpl userDetails = (UserDetailsImpl)  authDetailService
                .loadUserByUsername(authRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new AuthResponse(userDetails.getId(), userDetails.getUsername(), roles, token));
    }

    @PostMapping("/user/create")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserRequest userRequest) throws Exception {
        logger.info("create user {}", userRequest);

        User newUser = new User();
        newUser.setUsername(userRequest.getUsername());
        newUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        //TODO ERROR AL AÑADIR ROLE
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.getRoleByName("USER"));
        newUser.setRoles(roles);
        return ResponseEntity.ok( userMapper.entityToDTO(userRepository.save(newUser)));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            logger.error("---Disabled User---{}", e.getMessage());
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
