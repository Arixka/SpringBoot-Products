package com.maria.siverio.apirestproducts.security.service;



import com.maria.siverio.apirestproducts.users.models.User;
import com.maria.siverio.apirestproducts.users.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@Transactional
public class AuthDetailService implements UserDetailsService {
    //TODO cambiar nombre a UserTedailsSrviceImpl

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return  UserDetailsImpl.build(user);
    }

}

