package com.preview.preview.domain.web.controller;

import com.preview.preview.domain.service.authority.AuthorityServiceImpl;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.web.dto.authority.AuthorityResponseDto;
import com.preview.preview.global.auth.jwt.TokenProvider;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthorityController {

    private final AuthorityServiceImpl authorityService;

    public AuthorityController(AuthorityServiceImpl authorityService) {
        this.authorityService = authorityService;
    }

    @PostMapping("/authority/{kakaoId}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<AuthorityResponseDto> enroll(@PathVariable String kakaoId){
        AuthorityResponseDto authorityResponseDto = authorityService.enrollAuthority(kakaoId);
        return ResponseEntity.ok(authorityResponseDto);
    }
}
