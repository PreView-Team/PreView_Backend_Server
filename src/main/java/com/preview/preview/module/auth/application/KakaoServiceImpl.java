package com.preview.preview.module.auth.application;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import com.preview.preview.module.user.application.dto.social.kakao.KakaoLoginInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoServiceImpl{

    private final Gson gson;
    private final RestTemplate restTemplate;
    private final String KAKAO_USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";
    private final String KAKAO_USER_REFRESH_URL = "https://kauth.kakao.com/oauth/token";

    @Value("${external.api.client_id}")
    private String clientId;

    // token만 가지고 정보 얻을 수 있음.
    public KakaoLoginInfoDto getProfile(String token) {
        token = refreshToken(token);
        ResponseEntity<String> response = null;
        HttpHeaders header = new HttpHeaders(); 
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        header.set("Authorization", "Bearer " + token);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, header);

        try {
            response = restTemplate.exchange(KAKAO_USER_INFO_URL,HttpMethod.GET,request, String.class);
        } catch (HttpClientErrorException e){
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED){
                throw new CustomException(ErrorCode.UNAUTHORIZED_KAKAO_LOGIN);
            }
        }

        return gson.fromJson(response.getBody(), KakaoLoginInfoDto.class);
    }

    public String refreshToken(String token) {
        try {
            URL url = new URL(KAKAO_USER_REFRESH_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=refresh_token");
            sb.append("&client_id="+clientId);
            sb.append("&refresh_token="+token);
            bw.write(sb.toString());
            bw.flush();
            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);
            br.close();
            bw.close();
            token = element.getAsJsonObject().get("access_token").getAsString();

        } catch (Exception exception){
            throw new CustomException(ErrorCode.UNAUTHORIZED_KAKAO_REFRESH_TOKEN);
        }
        return token;
    }
}
