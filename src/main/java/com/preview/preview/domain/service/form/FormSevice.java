package com.preview.preview.domain.service.form;


import com.preview.preview.domain.web.dto.form.FormCreateRequestDto;
import com.preview.preview.domain.web.dto.form.FormCreateResponseDto;
import com.preview.preview.domain.web.dto.form.FormGetResponseDto;

import java.util.Optional;

public interface FormSevice {
    FormCreateResponseDto createForm(long userId, FormCreateRequestDto formCreateRequestDto);
    FormGetResponseDto getForm(long formId);
}
