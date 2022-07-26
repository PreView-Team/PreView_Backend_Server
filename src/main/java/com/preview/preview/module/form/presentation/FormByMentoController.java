package com.preview.preview.module.form.presentation;

import com.preview.preview.module.form.application.FormByMentoService;
import com.preview.preview.module.form.application.dto.FormAcceptStatusResponseDto;
import com.preview.preview.module.form.application.dto.FormsByMentoGetResponseDto;
import com.preview.preview.module.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mento")
public class FormByMentoController {
    private final FormByMentoService formByMentoService;

    public FormByMentoController(FormByMentoService formByMentoService) {
        this.formByMentoService = formByMentoService;
    }

    // 멘토에게 들어온 제안서 확인
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/form")
    public ResponseEntity<List<FormsByMentoGetResponseDto>> getFormsByMento(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(formByMentoService.getMemtoFormsByKakaoId(user.getKakaoId()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("form/{formId}")
    public ResponseEntity<FormAcceptStatusResponseDto> acceptForm(
            @PathVariable long formId,
            @AuthenticationPrincipal User user){
        return ResponseEntity.ok(formByMentoService.acceptForm(formId, user.getKakaoId()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @DeleteMapping("form/{formId}")
    public ResponseEntity<FormAcceptStatusResponseDto> rejectForm(
            @PathVariable long formId,
            @AuthenticationPrincipal User user){
        return ResponseEntity.ok(formByMentoService.rejectForm(formId, user.getKakaoId()));
    }

}
