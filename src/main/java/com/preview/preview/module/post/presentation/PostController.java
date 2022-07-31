package com.preview.preview.module.post.presentation;

import com.preview.preview.module.post.application.PostLikeService;
import com.preview.preview.module.post.application.PostsService;
import com.preview.preview.module.post.application.dto.*;
import com.preview.preview.module.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostController {

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

        @PreAuthorize("hasAnyRole('ROLE_USER')")
        @DeleteMapping("/api/post/{postId}")
        public ResponseEntity<PostDeleteResponseDto> deletePost(
                @AuthenticationPrincipal User user,
                @PathVariable Long postId){
                return ResponseEntity.ok(postsService.delete(user.getKakaoId(), postId));
        }

        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @GetMapping("/api/post/category")
        public ResponseEntity<List<PostsGetByCategoryResponseDto>> getPostsByCategory(
                @RequestParam("status") String status,
                @RequestParam("name") String name,
                @AuthenticationPrincipal User user){
                switch (status){
                        case "recommendation":
                                return ResponseEntity.ok(postsService.findRecommendedPostsByCategoryName(user.getKakaoId(), name));
                        case "new":
                                return ResponseEntity.ok(postsService.findPostsByCategoryName(user.getKakaoId(), name));
                }
                return null;
        }


        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @PostMapping("/api/post/like")
        public ResponseEntity<PostLikeResponseDto> likePost(
                @AuthenticationPrincipal User user,
                @RequestBody PostLikeRequestDto postLikeRequestDto){
                return ResponseEntity.ok(postLikeService.likePostByUserId(user.getKakaoId(), postLikeRequestDto));
        }

        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @PostMapping("/api/post/unlike")
        public ResponseEntity<PostUnLikeResponseDto> unlikePost(
                @AuthenticationPrincipal User user,
                @RequestBody PostLikeRequestDto postLikeRequestDto){
                return ResponseEntity.ok(postLikeService.unlikePostByUserId(user.getKakaoId(), postLikeRequestDto));
        }

        // 신규멘토, 카테고리 id 게시물 전체 조회

        // 추천멘토, 카테고리 id 게시물 전체 조회


}
