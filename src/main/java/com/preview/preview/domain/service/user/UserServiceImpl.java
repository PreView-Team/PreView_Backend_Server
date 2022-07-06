package com.preview.preview.domain.service.user;

import com.preview.preview.domain.authority.Authority;
import com.preview.preview.domain.enterprise.Enterprise;
import com.preview.preview.domain.job.Job;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.user.UserRepository;
import com.preview.preview.domain.web.dto.enterprise.EnterpriseDto;
import com.preview.preview.domain.web.dto.job.JobDto;
import com.preview.preview.domain.web.dto.user.UserDto;
import com.preview.preview.global.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDto signup(UserDto userDto){
        if (userRepository.findOneWithAuthoritiesByKakaoId(userDto.getKakaoId()).orElse(null) != null){
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickname(userDto.getNickname())
                .kakaoId(userDto.getKakaoId())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return UserDto.from(userRepository.save(user));
    }

    @Override
    public ResponseEntity<Boolean> checkNicknameDuplicate(String name) {
        return ResponseEntity.ok(userRepository.existsByNickname(name));
    }

    @Transactional
    public UserDto save(UserDto userDto){

        if (userRepository.findOneWithAuthoritiesByKakaoId(userDto.getKakaoId()).orElse(null) != null){
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Set<Job> jobs = new HashSet<>();
        for (JobDto s : userDto.getJobDtoSet()){
            jobs.add(Job.builder().name(s.getJobName()).build());
        }

        Set<Enterprise> enterprises = new HashSet<>();

        for (EnterpriseDto s : userDto.getEnterpriseDtoSet()){
            enterprises.add(Enterprise.builder().name(s.getEnterpriseName()).build());
        }

        User user = User.builder()
                .password(passwordEncoder.encode("xxxx")) // 추 후 확장, 지금 서비스에선 필요 x
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .likedJobs(jobs)
                .likedEnterprises(enterprises)
                .kakaoId(userDto.getKakaoId())
                .activated(true)
                .build();

        return userDto.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserDto getUserWithAuthorities(String username){
        return null;
    }

    @Transactional(readOnly = true)
    public UserDto getMyUserWithAuthorities(){
        return UserDto.from(SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByKakaoId).orElse(null));
    }

    @Override
    public Optional<User> findByIdPw(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByKakaoId(Long id){
        return userRepository.findOneWithAuthoritiesByKakaoId(id);
    }

    public boolean existedByEmail(String email){
        return userRepository.existsByEmail(email);
    }
}
