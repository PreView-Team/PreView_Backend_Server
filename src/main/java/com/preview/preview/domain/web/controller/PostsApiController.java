package com.preview.preview.domain.web.controller;

import com.preview.preview.domain.service.posts.PostsService;
import com.preview.preview.domain.web.dto.PostsResponseDto;
import com.preview.preview.domain.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class PostsApiController {

        private final PostsService postsService;

        @GetMapping("/board")
        public String index(){
                return "board";
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
