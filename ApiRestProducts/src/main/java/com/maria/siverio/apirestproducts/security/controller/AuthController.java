package com.maria.siverio.apirestproducts.security.controller;


import com.maria.siverio.apirestproducts.security.dtos.AuthRequest;
import com.maria.siverio.apirestproducts.security.dtos.AuthResponse;
import com.maria.siverio.apirestproducts.security.service.AuthDetailService;
import com.maria.siverio.apirestproducts.security.util.JwtTokenUtil;
import com.maria.siverio.apirestproducts.users.dtos.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthDetailService authDetailService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> createAuthenticationToken(@Valid @RequestBody AuthRequest authRequest) throws Exception {
// al primer login cambiar la pass?
        authenticate(authRequest.getUsername(), authRequest.getPassword());

        final UserDetails userDetails = authDetailService
                .loadUserByUsername(authRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/user/create")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody UserRequest userRequest) throws Exception {
        logger.info("create user {}", userRequest);
        return ResponseEntity.ok(authDetailService.addUser(userRequest));
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
