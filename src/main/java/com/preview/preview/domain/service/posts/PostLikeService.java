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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostLikeService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostLikeRepository postLikeRepository;

    public PostLikeService(PostRepository postRepository, UserRepository userRepository, PostLikeRepository postLikeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postLikeRepository = postLikeRepository;
    }

    @Transactional
    public PostLikeResponseDto likePostByUserId(PostLikeRequestDto postLikeRequestDto){
        User user = userRepository.findByKakaoId(postLikeRequestDto.getUserKakaoId()).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Post post = postRepository.findById(postLikeRequestDto.getPostId()).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_POST_ID));

        if (postLikeRepository.existsPostLikeByUserIdAndPostId(user.getId(), post.getId())){
            throw new CustomException(ErrorCode.DUPLICATE_POST_LIKE_RESOURCE);
        }

        PostLike postLike = PostLike.builder().post(post).user(user).build();
        postLikeRepository.save(postLike);
        PostLikeResponseDto postLikeResponseDto = new PostLikeResponseDto();
        postLikeResponseDto.setPostId(post.getId());
        postLikeResponseDto.setUserId(user.getId());
        postLikeResponseDto.setResult("좋아요 등록 성공");
        return postLikeResponseDto;
    }

    @Transactional
    public PostLikeResponseDto unlikePostByUserId(PostLikeRequestDto postLikeRequestDto){
        User user = userRepository.findByKakaoId(postLikeRequestDto.getUserKakaoId()).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Post post = postRepository.findById(postLikeRequestDto.getPostId()).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_POST_ID));

        PostLike postLike = postLikeRepository.findPostLikeByUserIdAndPostId(user.getId(), post.getId()).orElseThrow(() -> new CustomException(ErrorCode.DUPLICATE_POST_UNLIKE_RESOURCE));
        postLikeRepository.deleteById(postLike.getId());

        PostLikeResponseDto postLikeResponseDto = new PostLikeResponseDto();
        postLikeResponseDto.setPostId(post.getId());
        postLikeResponseDto.setUserId(user.getId());
        postLikeResponseDto.setResult("좋아요 취소 성공");
        return postLikeResponseDto;
    }

}
