package com.preview.preview.module.post.presentation;

import com.preview.preview.module.post.application.PostLikeService;
import com.preview.preview.module.post.application.PostsService;
import com.preview.preview.module.post.application.dto.*;
import com.preview.preview.module.post.domain.Post;
import com.preview.preview.module.post.domain.PostRepository;
import com.preview.preview.module.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        private final PostRepository postRepository;

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


        @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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
                @AuthenticationPrincipal User user,
                Pageable pageable){
                switch (status){
                        case "recommendation":
                                return ResponseEntity.ok(postsService.findRecommendedPostsByCategoryName(user.getKakaoId(), name, pageable));
                        case "new":
                                return ResponseEntity.ok(postsService.findPostsByCategoryName(user.getKakaoId(), name, pageable));
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

        // home 신규 멘토 주기
        @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
        @GetMapping("/api/home/post")
        public ResponseEntity<List<PostGetAtHomeResponseDto>> getPostsByHome(
                @RequestParam("status") String status,
                @AuthenticationPrincipal User user,
                Pageable pageable
                ){
                switch (status){
                        case "recommendation":
                                return ResponseEntity.ok(postsService.findPostsByRecommendMentor(user.getKakaoId(), pageable));
                        case "new":
                                return ResponseEntity.ok(postsService.findPostsByNewMentor(pageable));
                }
                return null;
        }
        // home 추천 멘토 주기

        // 정렬 기능


}
