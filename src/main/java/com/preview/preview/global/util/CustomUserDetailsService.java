package com.preview.preview.global.util;

import com.preview.preview.domain.user.User;
import com.preview.preview.domain.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String id) throws UsernameNotFoundException {
        return userRepository.findOneWithAuthoritiesByKakaoId(Long.parseLong(id))
                .map(user -> createUser(id, user))
                .orElseThrow(() -> new UsernameNotFoundException(id + " -> 데이터베이스에서 찾을 수 없습니다."));
    }

    private org.springframework.security.core.userdetails.User createUser(String id, User user){
        if (!user.isActivated()){
            throw new RuntimeException(id + " -> 활성화되어 있지 않습니다.");
        }
        List<GrantedAuthority> grantedAuthorityList = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        // kakao aouth id
        return new org.springframework.security.core.userdetails.User(user.getKakaoId().toString(),
                user.getPassword(),
                grantedAuthorityList);
    }
}
