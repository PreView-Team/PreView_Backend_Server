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
import com.preview.preview.domain.web.dto.post.PostsUpdateRequestDto;
import com.preview.preview.domain.web.dto.post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostRepository postsRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final PostLikeRepository postLikeRepository;


    @Transactional
    public PostCreateResponseDto save(long kakaoID, PostCreateRequestDto requestDto){
        Category category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(()-> {throw new CustomException(ErrorCode.NOT_EXISTED_CATEGORY_ID);
        });

        User user = userRepository.findByKakaoId(kakaoID).orElseThrow(()->{throw new CustomException(ErrorCode.NOT_EXISTED_USER_ID);});

        if (user.getAuthorities().size() == 1) throw new CustomException(ErrorCode.NOT_EQUAL_USER_RESOURCE);

        Post post = Post.builder()
                .category(category)
                .user(user)
                .content(requestDto.getContents())
                .title(requestDto.getTitle())
                .sub_title(requestDto.getSubTitle()).build();

        PostDto.from(postsRepository.save(post));
        PostCreateResponseDto postCreateResponseDto = new PostCreateResponseDto();
        postCreateResponseDto.setResult("성공");;
        postCreateResponseDto.setId(post.getId());
        return postCreateResponseDto;
    }

    @Transactional
    public PostUpdateResponseDto update(PostsUpdateRequestDto requestDto){
        Post post = postsRepository.findById(requestDto.getPostId()).filter(getPost -> getPost.getDeletedDate() == null).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_POST_ID));
        User user = userRepository.findByKakaoId(requestDto.getKakaoId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        // kakao ID랑 post user id 비교
        if (post.getUser().getId() != user.getId()) new CustomException(ErrorCode.NOT_EQUAL_USER_RESOURCE);

        post.setTitle(requestDto.getTitle());
        post.setSub_title(requestDto.getSubTitle());
        post.setContent(requestDto.getContents());

        PostDto.from(postsRepository.save(post));
        PostUpdateResponseDto postUpdateResponseDto = new PostUpdateResponseDto();
        postUpdateResponseDto.setResult("수정되었습니다.");
        postUpdateResponseDto.setId(post.getId());
        return postUpdateResponseDto;
    }

    @Transactional
    public PostGetResponseDto findById(Long userId, Long postId){
        User user = userRepository.findByKakaoId(userId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Post post = postsRepository.findById(postId).filter(getPost -> getPost.getDeletedDate() == null).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_POST_ID));
        PostGetResponseDto postGetResponseDto = new PostGetResponseDto();

        if (postLikeRepository.existsPostLikeByUserIdAndPostId(user.getId(), post.getId())){
            postGetResponseDto.setCheckedLike(true);
        } else{
            postGetResponseDto.setCheckedLike(false);
        }

        postGetResponseDto.setCategoryName(post.getCategory().getName());
        postGetResponseDto.setNickname(post.getUser().getNickname());
        postGetResponseDto.setTitle(post.getTitle());
        postGetResponseDto.setSubTitle(post.getSub_title());
        postGetResponseDto.setContents(post.getContent());
        postGetResponseDto.setCreateDateTime(post.getCreatedDate());
        postGetResponseDto.setUpdateDateTime(post.getModifiedDate());
        return postGetResponseDto;
    }

    @Transactional
    public List<PostsGetByCategoryResponseDto> findPostsByCategoryId(PostsGetByCategoryRequestDto postsGetByCategoryRequestDto){
        User user = userRepository.findByKakaoId(postsGetByCategoryRequestDto.getUserKakaoId()).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        List<Post> posts = postsRepository.findPostByCategoryId(postsGetByCategoryRequestDto.getCategoryId()).stream().filter(post -> post.getDeletedDate() == null).collect(Collectors.toList());
        boolean like = false;

        List<PostsGetByCategoryResponseDto> list = new ArrayList<>();

        for (Post s:posts){
            like = postLikeRepository.existsPostLikeByUserIdAndPostId(user.getId(), s.getId());
            list.add(PostsGetByCategoryResponseDto.of(s, like));
        }

        return list;
    }

    @Transactional
    public PostDeleteResponseDto delete(PostsDeleteRequestDto deleteRequestDto){

        PostDeleteResponseDto postDeleteResponseDto = new PostDeleteResponseDto();

        postsRepository.findById(deleteRequestDto.getPostId())
                .filter(unidentifiedPost -> unidentifiedPost.getDeletedDate() == null && unidentifiedPost.getUser().getKakaoId() == deleteRequestDto.getKakaoId())
                .map(post -> {
                    post.deletePost();
                    postsRepository.save(post);
                    postDeleteResponseDto.setId(deleteRequestDto.getPostId());
                    postDeleteResponseDto.setResult("게시글을 삭제하였습니다.");
                    return postDeleteResponseDto;
                }).orElseThrow(()-> new CustomException(ErrorCode.NOT_DELETE_POST_RESOURCE));

        return postDeleteResponseDto;
    }

}
