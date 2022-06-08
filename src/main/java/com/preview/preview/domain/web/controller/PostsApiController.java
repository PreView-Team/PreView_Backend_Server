package com.preview.preview.domain.web.controller;

import com.preview.preview.domain.service.posts.PostsService;
import com.preview.preview.domain.web.dto.PostsResponseDto;
import com.preview.preview.domain.web.dto.PostsSaveRequestDto;
import com.preview.preview.domain.web.dto.PostsUpdateRequestDto;
import com.preview.preview.global.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostsApiController {

        private final PostsService postsService;

        @GetMapping("/board")
        public String index(){
                return "board";
        }

        @PostMapping("/api/v1/posts")
        public Long save(@RequestBody PostsSaveRequestDto requestDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
                return postsService.save(requestDto, principalDetails.getUser());
        }

        @PutMapping("/api/v1/posts/{id}")
        public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
                return postsService.update(id, requestDto);
        }

        @GetMapping("/api/v1/posts/{id}")
        public PostsResponseDto findById(@PathVariable Long id){
                return postsService.findById(id);
        }

}
