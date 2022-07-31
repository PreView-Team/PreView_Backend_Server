package com.preview.preview.module.mentor;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import com.preview.preview.module.auth.domain.Authority;
import com.preview.preview.module.user.domain.User;
import com.preview.preview.module.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorityServiceImpl{

    private final UserRepository userRepository;
    private final MentorRepository mentorRepository;

    public AuthorityServiceImpl(UserRepository userRepository, MentorRepository mentorRepository) {
        this.userRepository = userRepository;
        this.mentorRepository = mentorRepository;
    }

    @Transactional
    public AuthorityResponseDto enrollAuthority(String kakaoId) {

        User user = userRepository.findOneWithAuthoritiesByKakaoId(Long.valueOf(kakaoId)).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Mentor mentor = Mentor.builder().contents("").nickname(user.getNickname()).build();

        for (Authority s : user.getAuthorities()){
            if(s.getAuthorityName().equals("ROLE_ADMIN")) throw new CustomException(ErrorCode.DUPLICATE_AUTHORITY_RESOURCE);
        }

        user.getAuthorities().add(new Authority("ROLE_ADMIN"));
        mentorRepository.save(mentor);
        userRepository.save(user);

        return AuthorityResponseDto.from(user);
    }
}
