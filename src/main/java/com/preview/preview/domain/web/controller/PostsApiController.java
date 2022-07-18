package com.preview.preview.domain.web.controller;

import com.preview.preview.domain.service.posts.PostsService;
import com.preview.preview.domain.web.dto.post.PostsUpdateRequestDto;
import com.preview.preview.domain.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostsApiController {

        private final PostsService postsService;

        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
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

        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @PutMapping("/api/post")
        public ResponseEntity<PostUpdateResponseDto> updatePost(
                @RequestBody PostsUpdateRequestDto postsUpdateRequestDto){
                return ResponseEntity.ok(postsService.update(postsUpdateRequestDto));
        }

        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @DeleteMapping("/api/post")
        public ResponseEntity<PostDeleteResponseDto> deletePost(
                @RequestBody PostsDeleteRequestDto postsDeleteRequestDto){
                return ResponseEntity.ok(postsService.delete(postsDeleteRequestDto));
        }




}
