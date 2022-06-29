package com.preview.preview.domain.service.social;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.preview.preview.domain.web.dto.social.kakao.KakaoLoginRequestDto;
import com.preview.preview.domain.web.dto.social.kakao.KakaoProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoService {

    private final RestTemplate restTemplate;
    private final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    public String getProfile(String token) {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.set("Authorization", "Bearer " + token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, header);

        try {
            ResponseEntity<String> response = restTemplate.exchange(KAKAO_USER_INFO_URL,HttpMethod.GET,request, String.class);
            if (response.getStatusCode() == HttpStatus.OK)
                return response.getBody();
        } catch (Exception e) {
            log.error(e.toString());
            throw e;
        }
        return null;
    }
}