package com.preview.preview.module.post.application;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import com.preview.preview.module.post.application.dto.PostLikeRequestDto;
import com.preview.preview.module.post.application.dto.PostLikeResponseDto;
import com.preview.preview.module.post.application.dto.PostUnLikeResponseDto;
import com.preview.preview.module.post.domain.Post;
import com.preview.preview.module.post.domain.PostLike;
import com.preview.preview.module.post.domain.PostLikeRepository;
import com.preview.preview.module.post.domain.PostRepository;
import com.preview.preview.module.user.domain.User;
import com.preview.preview.module.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostLikeService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;

    public PostLikeService(PostRepository postRepository, UserRepository userRepository, PostLikeRepository postLikeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postLikeRepository = postLikeRepository;
    }

    @Transactional
    public PostLikeResponseDto likePostByUserId(long kakaoId, PostLikeRequestDto postLikeRequestDto){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Post post = postRepository.findById(postLikeRequestDto.getPostId()).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_POST_ID));

        if (postLikeRepository.existsPostLikeByUserIdAndPostId(user.getId(), post.getId())){
            throw new CustomException(ErrorCode.DUPLICATE_POST_LIKE_RESOURCE);
        }

        PostLike postLike = PostLike.builder().post(post).user(user).build();
        postLikeRepository.save(postLike);
        return PostLikeResponseDto.from(postLike);
    }

    @Transactional
    public PostUnLikeResponseDto unlikePostByUserId(long kakaoId, PostLikeRequestDto postLikeRequestDto){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Post post = postRepository.findById(postLikeRequestDto.getPostId()).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_POST_ID));

        PostLike postLike = postLikeRepository.findPostLikeByUserIdAndPostId(user.getId(), post.getId()).orElseThrow(() -> new CustomException(ErrorCode.DUPLICATE_POST_UNLIKE_RESOURCE));
        postLikeRepository.deleteById(postLike.getId());

        return PostUnLikeResponseDto.from(postLike);
    }

}
