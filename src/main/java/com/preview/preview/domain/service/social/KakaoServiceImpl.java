package com.preview.preview.domain.service.social;


import com.google.gson.Gson;
import com.preview.preview.domain.service.social.kakao.KakaoService;
import com.preview.preview.domain.web.dto.social.kakao.KakaoLoginInfoDto;
import com.preview.preview.domain.web.dto.social.kakao.KakaoLoginRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoServiceImpl implements KakaoService {

    private final Gson gson;
    private final RestTemplate restTemplate;
    private final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    // token만 가지고 정보 얻을 수 있음.
    @Override
    public KakaoLoginInfoDto getProfile(KakaoLoginRequestDto kakaoLoginRequestDto) {
        ResponseEntity<String> response = null;
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.set("Authorization", "Bearer " + kakaoLoginRequestDto.getToken());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, header);

        try {
            response = restTemplate.exchange(KAKAO_USER_INFO_URL,HttpMethod.GET,request, String.class);
        } catch (Exception e) {
            throw e;
        }
        return gson.fromJson(response.getBody(), KakaoLoginInfoDto.class);
    }
}