package com.preview.preview.module.mentor.application;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import com.preview.preview.module.auth.domain.Authority;
import com.preview.preview.module.job.domain.MentorJobRepository;
import com.preview.preview.module.mentor.application.dto.MentorGetResponseDto;
import com.preview.preview.module.mentor.application.dto.MentorUpdateRequestDto;
import com.preview.preview.module.mentor.application.dto.MentorUpdateResponseDto;
import com.preview.preview.module.mentor.domain.Mentor;
import com.preview.preview.module.mentor.domain.MentorRepository;
import com.preview.preview.module.mentor.application.dto.MentorResponseDto;
import com.preview.preview.module.user.application.dto.VaildedNicknameDto;
import com.preview.preview.module.user.domain.User;
import com.preview.preview.module.user.domain.UserRepository;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MentorService{

    private final UserRepository userRepository;
    private final MentorRepository mentorRepository;

    public MentorService(UserRepository userRepository, MentorRepository mentorRepository) {
        this.userRepository = userRepository;
        this.mentorRepository = mentorRepository;
    }

    @Transactional
    public MentorResponseDto enrollAuthority(String kakaoId) {

        User user = userRepository.findOneWithAuthoritiesByKakaoId(Long.valueOf(kakaoId)).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Mentor mentor = Mentor.builder().contents("").nickname(user.getNickname()).build();
        user.setMentor(mentor);
        for (Authority s : user.getAuthorities()){
            if(s.getAuthorityName().equals("ROLE_ADMIN")) throw new CustomException(ErrorCode.DUPLICATE_AUTHORITY_RESOURCE);
        }

        user.getAuthorities().add(new Authority("ROLE_ADMIN"));
        mentorRepository.save(mentor);
        userRepository.save(user);

        return MentorResponseDto.from(user);
    }

    @Transactional
    public MentorGetResponseDto getMentorProfile(long mentorId){
        Mentor mentor = mentorRepository.findMentorById(mentorId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_MENTOR_ID));
        return MentorGetResponseDto.from(mentor);
    }

    @Transactional
    public MentorGetResponseDto getMentorInfo(long kakaoId){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Optional<Mentor> mentor = Optional.ofNullable(user.getMentor());
        if (mentor.isEmpty()) throw new CustomException(ErrorCode.NOT_EXISTED_MENTOR_ID);

        return MentorGetResponseDto.from(user.getMentor());
    }

    @Transactional
    public VaildedNicknameDto checkValidedNickname(Long kakaoId, String username){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));

        if(user.getNickname().equals(username) == false && userRepository.existsByNickname(username)) return VaildedNicknameDto.from(false);
        else if (mentorRepository.existsMentorByNickname(username)) return VaildedNicknameDto.from(false);
        return VaildedNicknameDto.from(true);
    }

    @Transactional
    public MentorUpdateResponseDto updateMentorProfile(long kakaoId, MentorUpdateRequestDto mentorUpdateRequestDto){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Mentor mentor = mentorRepository.findMentorById(user.getMentor().getId()).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_MENTOR_ID));

        mentor.setLikedJobs(mentorUpdateRequestDto.getJobDtoSet());
        mentor.setContents(mentorUpdateRequestDto.getContents());
        mentor.setNickname(mentorUpdateRequestDto.getNickname());

        try {
            return MentorUpdateResponseDto.from(mentorRepository.save(mentor));
        }catch (JpaObjectRetrievalFailureException ex){
            throw new CustomException(ErrorCode.NOT_EXISTED_LIKE_JOB);
        }
    }
}
