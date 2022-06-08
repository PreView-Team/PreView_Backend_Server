package com.preview.preview.global.auth;

import com.preview.preview.domain.posts.Posts;
import com.preview.preview.domain.user.Role;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.user.UserRepository;
import com.preview.preview.domain.web.dto.PostsResponseDto;
import com.preview.preview.domain.web.dto.UserResponseDto;
import com.preview.preview.global.auth.userinfo.KakaoUserInfo;
import com.preview.preview.global.auth.userinfo.OAuth2UserInfo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PrincipalOauth2UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserInfo oAuth2UserInfo = null;	//추가
        String provider = userRequest.getClientRegistration().getRegistrationId();
        System.out.printf("hihihi");
        //추가
        if(provider.equals("kakao")){
            System.out.printf("hihihi");
            oAuth2UserInfo = new KakaoUserInfo(oAuth2User.getAttributes());
        }


        String providerId = oAuth2UserInfo.getProviderId();	//수정
        String username = provider+"_"+providerId;

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("패스워드"+uuid);

        String email = oAuth2UserInfo.getEmail();	//수정
        Role role = Role.ROLE_USER;

        User byUsername = userRepository.findByName(username);

        //DB에 없는 사용자라면 회원가입처리
        if(byUsername == null){
            byUsername = User.oauth2Register()
                    .username(username).password(password).email(email).role(role)
                    .provider(provider).providerId(providerId)
                    .build();
            userRepository.save(byUsername);
        }

        return new PrincipalDetails(byUsername, oAuth2UserInfo);	//수정
    }

    public UserResponseDto findById(Long id){
        User entity = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다. id="+id));
        return new UserResponseDto(entity);
    }

}