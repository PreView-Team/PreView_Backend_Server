package com.preview.preview.domain.service.form;


import com.preview.preview.domain.web.dto.form.FormCreateRequestDto;
import com.preview.preview.domain.web.dto.form.FormCreateResponseDto;

import java.util.Optional;

public interface FormSevice {
    FormCreateResponseDto createForm(long userId, FormCreateRequestDto formCreateRequestDto);
}
