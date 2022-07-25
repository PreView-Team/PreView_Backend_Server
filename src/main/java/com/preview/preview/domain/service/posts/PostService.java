package com.preview.preview.domain.service.posts;

import com.preview.preview.domain.web.dto.post.*;

import java.util.List;

public interface PostService {
    // 멘토 게시물 저장하는 메소드
    public PostCreateResponseDto save(long kakaoID, PostCreateRequestDto requestDto);
    // 멘토 게시물 업데이트하는 메소드
    public PostUpdateResponseDto update(long kakaoId, PostsUpdateRequestDto requestDto);
    // 멘토 게시물 조회하는 메소드
    public PostGetResponseDto findById(Long kakaoId, Long postId);
    // 카테고리 별로 멘토 게시물 전체 조회하는 메소드
    public List<PostsGetByCategoryResponseDto> findPostsByCategoryId(PostsGetByCategoryRequestDto postsGetByCategoryRequestDto);
    // 멘토 게시물 삭제하는 메소드
    public PostDeleteResponseDto delete(long kakaoId, PostsDeleteRequestDto deleteRequestDto);
}
