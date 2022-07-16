package com.preview.preview.domain.service.authority;

import com.preview.preview.domain.authority.Authority;
import com.preview.preview.domain.exception.CustomException;
import com.preview.preview.domain.exception.ErrorCode;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.user.UserRepository;
import com.preview.preview.domain.web.dto.authority.AuthorityResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    private final UserRepository userRepository;

    public AuthorityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public AuthorityResponseDto enrollAuthority(String kakaoId) {
        // 유저 확인
        User user = userRepository.findOneWithAuthoritiesByKakaoId(Long.valueOf(kakaoId)).orElse(null);

        if (user == null){
            throw new CustomException(ErrorCode.NOT_EXISTED_USER_ID);
        }

        for (Authority s : user.getAuthorities()){
            if(s.getAuthorityName().equals("ROLE_ADMIN")) throw new CustomException(ErrorCode.DUPLICATE_AUTHORITY_RESOURCE);
        }

        user.getAuthorities().add(new Authority("ROLE_ADMIN"));

        userRepository.save(user);

        AuthorityResponseDto authorityResponseDto = new AuthorityResponseDto();
        authorityResponseDto.setResult("멘토 처리 되었습니다.");
        return authorityResponseDto;
    }
}
