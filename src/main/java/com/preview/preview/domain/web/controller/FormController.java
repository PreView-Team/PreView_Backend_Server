package com.preview.preview.domain.web.controller;

import com.preview.preview.domain.service.form.FormServiceImpl;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.web.dto.form.FormCreateRequestDto;
import com.preview.preview.domain.web.dto.form.FormCreateResponseDto;
import com.preview.preview.domain.web.dto.form.FormGetResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/form")
public class FormController {
    private final FormServiceImpl formService;

    public FormController(FormServiceImpl formService) {
        this.formService = formService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping
    public ResponseEntity<FormCreateResponseDto> createForm(
            @AuthenticationPrincipal User user,
            @RequestBody FormCreateRequestDto formCreateRequestDto){

        return ResponseEntity.ok(formService.createForm(user.getKakaoId(), formCreateRequestDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("{formId}")
    public ResponseEntity<FormGetResponseDto> getForm(
            @PathVariable long formId
    ){
        return ResponseEntity.ok(formService.getForm(formId));
    }

}
