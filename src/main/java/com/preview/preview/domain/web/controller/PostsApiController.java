package com.preview.preview.domain.web.controller;

import com.preview.preview.domain.service.posts.PostLikeService;
import com.preview.preview.domain.service.posts.PostsService;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.web.dto.post.PostsUpdateRequestDto;
import com.preview.preview.domain.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class PostsApiController {

        private final PostsService postsService;
        private final PostLikeService postLikeService;

        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @PostMapping("/api/post")
        public ResponseEntity<PostCreateResponseDto> createPost(
                @AuthenticationPrincipal User user,
                @RequestBody PostCreateRequestDto postCreateRequestDto){
                return ResponseEntity.ok(postsService.save(user.getKakaoId(), postCreateRequestDto));
        }

        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @GetMapping("/api/post/{postId}")
        public ResponseEntity<PostGetResponseDto> getPost(
                @AuthenticationPrincipal User user,
                @PathVariable Long postId){
                return ResponseEntity.ok(postsService.findById(user.getKakaoId(), postId));
        }

        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @PutMapping("/api/post")
        public ResponseEntity<PostUpdateResponseDto> updatePost(
                @AuthenticationPrincipal User user,
                @RequestBody PostsUpdateRequestDto postsUpdateRequestDto){
                return ResponseEntity.ok(postsService.update(user.getKakaoId(), postsUpdateRequestDto));
        }

        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @DeleteMapping("/api/post")
        public ResponseEntity<PostDeleteResponseDto> deletePost(
                @AuthenticationPrincipal User user,
                @RequestBody PostsDeleteRequestDto postsDeleteRequestDto){
                return ResponseEntity.ok(postsService.delete(user.getKakaoId(), postsDeleteRequestDto));
        }

        // 카테고리 별로 리스트 가져오기
        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @GetMapping("/api/post/category/{categoryId}")
        public ResponseEntity<List<PostsGetByCategoryResponseDto>> getPostsByCategory(
                @AuthenticationPrincipal User user,
                @PathVariable Long categoryId){
                PostsGetByCategoryRequestDto postsGetByCategoryRequestDto = new PostsGetByCategoryRequestDto();
                postsGetByCategoryRequestDto.setUserKakaoId(user.getKakaoId());
                postsGetByCategoryRequestDto.setCategoryId(categoryId);
                return ResponseEntity.ok(postsService.findPostsByCategoryId(postsGetByCategoryRequestDto));
        }

        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @PostMapping("/api/post/like")
        public ResponseEntity<PostLikeResponseDto> likePost(
                @RequestBody PostLikeRequestDto postLikeRequestDto){
                return ResponseEntity.ok(postLikeService.likePostByUserId(postLikeRequestDto));
        }

        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @PostMapping("/api/post/unlike")
        public ResponseEntity<PostLikeResponseDto> unlikePost(
                @RequestBody PostLikeRequestDto postLikeRequestDto){
                return ResponseEntity.ok(postLikeService.unlikePostByUserId(postLikeRequestDto));
        }


}
