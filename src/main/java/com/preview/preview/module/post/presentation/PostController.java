package com.preview.preview.module.post.presentation;

import com.preview.preview.module.post.application.PostLikeServiceImpl;
import com.preview.preview.module.post.application.PostsServiceImpi;
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

        private final PostsServiceImpi postsService;
        private final PostLikeServiceImpl postLikeService;

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


}
