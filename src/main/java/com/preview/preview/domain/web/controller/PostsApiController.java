package com.preview.preview.domain.web.controller;

import com.preview.preview.domain.service.posts.PostsService;
import com.preview.preview.domain.web.dto.PostsResponseDto;
import com.preview.preview.domain.web.dto.PostsUpdateRequestDto;
import com.preview.preview.domain.web.dto.post.PostCreateRequestDto;
import com.preview.preview.domain.web.dto.post.PostCreateResponseDto;
import com.preview.preview.domain.web.dto.post.PostDto;
import com.preview.preview.domain.web.dto.post.PostGetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostsApiController {

        private final PostsService postsService;

        // 멘토만 등록할 수 있게 설정
        @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
        @PostMapping("/api/post")
        public ResponseEntity<PostCreateResponseDto> createPost(
                @RequestBody PostCreateRequestDto postCreateRequestDto){
                return ResponseEntity.ok(postsService.save(postCreateRequestDto));
        }

        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @GetMapping("/api/post/{postId}")
        public ResponseEntity<PostGetResponseDto> getPost(
                @PathVariable Long postId){
                return ResponseEntity.ok(postsService.findById(postId));
        }



        @GetMapping("/board")
        public String index(){
                return "board";
        }

        @PutMapping("/api/v1/posts/{id}")
        public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
                return postsService.update(id, requestDto);
        }

}
