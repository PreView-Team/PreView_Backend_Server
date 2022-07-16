package com.preview.preview.domain.service.authority;

import com.preview.preview.domain.web.dto.authority.AuthorityResponseDto;

public interface AuthorityService {
    // 멘토 등록
    public AuthorityResponseDto enrollAuthority(String kakaoId);
}
