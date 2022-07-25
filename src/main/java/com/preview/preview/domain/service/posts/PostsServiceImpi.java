package com.preview.preview.domain.service.posts;


import com.preview.preview.domain.category.Category;
import com.preview.preview.domain.category.CategoryRepository;
import com.preview.preview.domain.exception.CustomException;
import com.preview.preview.domain.exception.ErrorCode;
import com.preview.preview.domain.post.Post;
import com.preview.preview.domain.post.PostLikeRepository;
import com.preview.preview.domain.post.PostRepository;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.user.UserRepository;
import com.preview.preview.domain.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsServiceImpi implements PostService{
    private final PostRepository postsRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PostLikeRepository postLikeRepository;

    @Transactional
    @Override
    public PostCreateResponseDto save(long kakaoID, PostCreateRequestDto requestDto){

        Category category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(()-> {throw new CustomException(ErrorCode.NOT_EXISTED_CATEGORY_ID);});

        User user = userRepository.findByKakaoId(kakaoID).orElseThrow(()->{throw new CustomException(ErrorCode.NOT_EXISTED_USER_ID);});

        if (user.getAuthorities().size() == 1) throw new CustomException(ErrorCode.NOT_EQUAL_USER_RESOURCE);

        Post post = Post.builder()
                .category(category)
                .user(user)
                .content(requestDto.getContents())
                .title(requestDto.getTitle())
                .sub_title(requestDto.getSubTitle()).build();

        return PostCreateResponseDto.from(postsRepository.save(post));
    }

    @Transactional
    @Override
    public PostUpdateResponseDto update(long kakaoId, PostsUpdateRequestDto requestDto){
        Post post = postsRepository.findById(requestDto.getPostId()).filter(getPost -> getPost.getDeletedDate() == null).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_POST_ID));
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));

        // kakao id, post에 있는 user id 비교 다를 시 예외처리
        if (post.getUser().getId() != user.getId()) new CustomException(ErrorCode.NOT_EQUAL_USER_RESOURCE);

        post.setTitle(requestDto.getTitle());
        post.setSub_title(requestDto.getSubTitle());
        post.setContent(requestDto.getContents());

        return PostUpdateResponseDto.from(postsRepository.save(post));
    }

    @Transactional
    @Override
    public PostGetResponseDto findById(Long kakaoId, Long postId){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Post post = postsRepository.findById(postId).filter(getPost -> getPost.getDeletedDate() == null).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_POST_ID));
        boolean isLiked;

        if (postLikeRepository.existsPostLikeByUserIdAndPostId(user.getId(), post.getId())){
            isLiked = true;
        } else{
            isLiked = false;
        }

        return PostGetResponseDto.from(post, isLiked);
    }

    @Transactional
    @Override
    public List<PostsGetByCategoryResponseDto> findPostsByCategoryId(PostsGetByCategoryRequestDto postsGetByCategoryRequestDto){
        User user = userRepository.findByKakaoId(postsGetByCategoryRequestDto.getUserKakaoId()).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        List<Post> posts = postsRepository.findPostByCategoryId(postsGetByCategoryRequestDto.getCategoryId()).stream().filter(post -> post.getDeletedDate() == null).collect(Collectors.toList());
        boolean like;

        List<PostsGetByCategoryResponseDto> list = new ArrayList<>();

        for (Post s:posts){
            like = postLikeRepository.existsPostLikeByUserIdAndPostId(user.getId(), s.getId());
            list.add(PostsGetByCategoryResponseDto.from(s, like));
        }

        return list;
    }

    @Transactional
    @Override
    public PostDeleteResponseDto delete(long kakaoId, PostsDeleteRequestDto deleteRequestDto){
        return postsRepository.findById(deleteRequestDto.getPostId())
                .filter(unidentifiedPost -> unidentifiedPost.getDeletedDate() == null && unidentifiedPost.getUser().getKakaoId() == kakaoId)
                .map(post -> {
                    post.deletePost();
                    postsRepository.save(post);
                    return PostDeleteResponseDto.from(post);
                }).orElseThrow(()-> new CustomException(ErrorCode.NOT_DELETE_POST_RESOURCE));
    }

}
