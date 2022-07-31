package com.preview.preview.module.mentor.presentation;
import com.preview.preview.module.mentor.application.dto.MentorGetResponseDto;
import com.preview.preview.module.mentor.application.dto.MentorResponseDto;
import com.preview.preview.module.mentor.application.MentorService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MentorController {

    private final MentorService mentorService;

    public MentorController(MentorService mentorService) {
        this.mentorService = mentorService;
    }


    @PostMapping("/mentor/{kakaoId}")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<MentorResponseDto> enroll(@PathVariable String kakaoId){
        MentorResponseDto authorityResponseDto = mentorService.enrollAuthority(kakaoId);
        return ResponseEntity.ok(authorityResponseDto);
    }

    // 조회하기
    @GetMapping("/mentor/{mentorId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<MentorGetResponseDto> getMentor(@PathVariable long mentorId){
        return ResponseEntity.ok(mentorService.getMentorProfile(mentorId));
    }
    // 업데이트

    // 해지하기
}
