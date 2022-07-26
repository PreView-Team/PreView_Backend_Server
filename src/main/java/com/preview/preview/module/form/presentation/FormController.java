package com.preview.preview.module.form.presentation;

import com.preview.preview.module.form.application.FormServiceImpl;
import com.preview.preview.module.form.application.dto.*;
import com.preview.preview.module.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @PathVariable long formId){
        return ResponseEntity.ok(formService.getForm(formId));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<List<FormAllGetResponseDto>> getForms(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(formService.getFormsByKakaoId(user.getKakaoId()));
    }

    // 신청서 수정
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping("{formId}")
    public ResponseEntity<FormUpdateResponseDto> updateForm(
            @AuthenticationPrincipal User user,
            @PathVariable long formId,
            @RequestBody FormUpdateRequestDto formUpdateRequestDto){
        return ResponseEntity.ok(formService.getUpdate(user.getKakaoId(), formId, formUpdateRequestDto));
    }
    // 신청서 삭제
}
