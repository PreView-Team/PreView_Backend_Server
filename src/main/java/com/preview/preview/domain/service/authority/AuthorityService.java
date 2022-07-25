package com.preview.preview.domain.service.authority;

import com.preview.preview.domain.web.dto.authority.AuthorityResponseDto;

public interface AuthorityService {
    // kakao id로 멘토 등록하는 메소드
    public AuthorityResponseDto enrollAuthority(String kakaoId);
}
