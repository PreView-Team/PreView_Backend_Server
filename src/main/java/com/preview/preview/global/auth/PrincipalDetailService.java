package com.preview.preview.global.auth;

import com.preview.preview.domain.user.User;
import com.preview.preview.domain.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public PrincipalDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepository.findByName(username);
        if(byUsername != null){
            return new PrincipalDetails(byUsername);
        }
        return null;
    }
}
