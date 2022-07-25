package com.preview.preview.domain.service.posts;

import com.preview.preview.domain.exception.CustomException;
import com.preview.preview.domain.exception.ErrorCode;
import com.preview.preview.domain.post.Post;
import com.preview.preview.domain.post.PostLike;
import com.preview.preview.domain.post.PostLikeRepository;
import com.preview.preview.domain.post.PostRepository;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.user.UserRepository;
import com.preview.preview.domain.web.dto.post.PostLikeRequestDto;
import com.preview.preview.domain.web.dto.post.PostLikeResponseDto;
import com.preview.preview.domain.web.dto.post.PostUnLikeResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostLikeServiceImpl implements PostLikeService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;

    public PostLikeServiceImpl(PostRepository postRepository, UserRepository userRepository, PostLikeRepository postLikeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postLikeRepository = postLikeRepository;
    }

    @Transactional
    @Override
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
    @Override
    public PostUnLikeResponseDto unlikePostByUserId(long kakaoId, PostLikeRequestDto postLikeRequestDto){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Post post = postRepository.findById(postLikeRequestDto.getPostId()).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_POST_ID));

        PostLike postLike = postLikeRepository.findPostLikeByUserIdAndPostId(user.getId(), post.getId()).orElseThrow(() -> new CustomException(ErrorCode.DUPLICATE_POST_UNLIKE_RESOURCE));
        postLikeRepository.deleteById(postLike.getId());

        return PostUnLikeResponseDto.from(postLike);
    }

}
