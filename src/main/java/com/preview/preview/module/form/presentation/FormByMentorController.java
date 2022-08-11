package com.preview.preview.module.form.presentation;

import com.preview.preview.module.form.application.FormByMentorService;
import com.preview.preview.module.form.application.dto.FormAcceptStatusResponseDto;
import com.preview.preview.module.form.application.dto.FormByMentorGetResponseDto;
import com.preview.preview.module.form.application.dto.MentorFormsByMentoGetResponseDto;
import com.preview.preview.module.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mentor")
public class FormByMentorController {
    private final FormByMentorService formByMentorService;

    public FormByMentorController(FormByMentorService formByMentorService) {
        this.formByMentorService = formByMentorService;
    }

    // 멘토에게 들어온 제안서 확인
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/form")
    public ResponseEntity<List<MentorFormsByMentoGetResponseDto>> getFormsByMentor(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(formByMentorService.getMemtorFormsByKakaoId(user.getKakaoId()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PostMapping("form/{formId}")
    public ResponseEntity<FormAcceptStatusResponseDto> acceptForm(
            @PathVariable long formId,
            @AuthenticationPrincipal User user){
        return ResponseEntity.ok(formByMentorService.acceptForm(formId, user.getKakaoId()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @DeleteMapping("form/{formId}")
    public ResponseEntity<FormAcceptStatusResponseDto> rejectForm(
            @PathVariable long formId,
            @AuthenticationPrincipal User user){
        return ResponseEntity.ok(formByMentorService.rejectForm(formId, user.getKakaoId()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("form/{formId}")
    public ResponseEntity<FormByMentorGetResponseDto> getForm(
            @PathVariable long formId,
            @AuthenticationPrincipal User user){
        return ResponseEntity.ok(formByMentorService.getForm(formId, user.getKakaoId()));
    }
}
