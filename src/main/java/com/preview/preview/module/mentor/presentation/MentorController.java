package com.preview.preview.module.mentor.presentation;
import com.preview.preview.module.mentor.application.dto.MentorGetResponseDto;
import com.preview.preview.module.mentor.application.dto.MentorResponseDto;
import com.preview.preview.module.mentor.application.MentorService;
import com.preview.preview.module.mentor.application.dto.MentorUpdateRequestDto;
import com.preview.preview.module.mentor.application.dto.MentorUpdateResponseDto;
import com.preview.preview.module.post.application.PostsService;
import com.preview.preview.module.post.application.dto.PostGetByMentorResponseDto;
import com.preview.preview.module.post.application.dto.PostsGetByMentorIdResponseDto;
import com.preview.preview.module.user.application.dto.UserDto;
import com.preview.preview.module.user.application.dto.VaildedNicknameDto;
import com.preview.preview.module.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MentorController {

    private final MentorService mentorService;
    private final PostsService postsService;

    public MentorController(MentorService mentorService, PostsService postsService) {
        this.mentorService = mentorService;
        this.postsService = postsService;
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

    @GetMapping("/mentor")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<MentorGetResponseDto> getMentorInfo(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(mentorService.getMentorInfo(user.getKakaoId()));
    }

    // 업데이트
    @PutMapping("/mentor")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<MentorUpdateResponseDto> updateMentor(@AuthenticationPrincipal User user,
                                                                @RequestBody MentorUpdateRequestDto mentorUpdateRequestDto){
        return ResponseEntity.ok(mentorService.updateMentorProfile(user.getKakaoId(), mentorUpdateRequestDto));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/mentor/post")
    public ResponseEntity<List<PostsGetByMentorIdResponseDto>> getPosts(
            @AuthenticationPrincipal User user
    ){
        return ResponseEntity.ok(postsService.getPostsBykakaoId(user.getKakaoId()));
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/mentor/post/{postId}")
    public ResponseEntity<PostGetByMentorResponseDto> getPost(
            @PathVariable Long postId
    ){
        return ResponseEntity.ok(postsService.getPostByMentor(postId));
    }

    @GetMapping("/mentor/nickname/{nickname}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<VaildedNicknameDto> isValidMentorNickname(@AuthenticationPrincipal User user, @PathVariable String nickname){
        return ResponseEntity.ok(mentorService.checkValidedNickname(user.getKakaoId(), nickname));
    }

}
