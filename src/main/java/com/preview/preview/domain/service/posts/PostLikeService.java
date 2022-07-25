package com.preview.preview.domain.service.posts;

import com.preview.preview.domain.web.dto.post.PostLikeRequestDto;
import com.preview.preview.domain.web.dto.post.PostLikeResponseDto;
import com.preview.preview.domain.web.dto.post.PostUnLikeResponseDto;

public interface PostLikeService {

    // 등록된 유저 카카오 id로 멘토 게시물 좋아요 하는 메소드
    public PostLikeResponseDto likePostByUserId(long kakaoId, PostLikeRequestDto postLikeRequestDto);
    // 등록된 유저 카카오 id로 멘토 게시물 좋아요 취소 하는 메소드
    public PostUnLikeResponseDto unlikePostByUserId(long kakaoId, PostLikeRequestDto postLikeRequestDto);

}
