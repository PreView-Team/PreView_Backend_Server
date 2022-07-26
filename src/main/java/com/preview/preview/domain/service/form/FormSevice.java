package com.preview.preview.domain.service.form;


import com.preview.preview.domain.web.dto.form.*;

import java.util.List;

public interface FormSevice {

    // 멘티 신청서 내용 기반으로 신청서 저장
    FormCreateResponseDto createForm(long userId, FormCreateRequestDto formCreateRequestDto);
    // 멘티 신청서 상세 조회
    FormGetResponseDto getForm(long formId);
    // 멘티 신청서 전체 조회
    List<FormAllGetResponseDto> getFormsByKakaoId(long kakaoId);
    // 멘티 신청서 수정
    FormUpdateResponseDto getUpdate(long kakaoId, long formId, FormUpdateRequestDto formUpdateRequestDto);
}
