package com.preview.preview.domain.service.posts;


import com.preview.preview.domain.category.Category;
import com.preview.preview.domain.category.CategoryRepository;
import com.preview.preview.domain.exception.CustomException;
import com.preview.preview.domain.exception.ErrorCode;
import com.preview.preview.domain.post.Post;
import com.preview.preview.domain.post.PostRepository;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.user.UserRepository;
import com.preview.preview.domain.web.dto.PostsResponseDto;
import com.preview.preview.domain.web.dto.PostsSaveRequestDto;
import com.preview.preview.domain.web.dto.PostsUpdateRequestDto;
import com.preview.preview.domain.web.dto.post.PostCreateRequestDto;
import com.preview.preview.domain.web.dto.post.PostCreateResponseDto;
import com.preview.preview.domain.web.dto.post.PostDto;
import com.preview.preview.domain.web.dto.post.PostGetResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostRepository postsRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    @Transactional
    public PostCreateResponseDto save(PostCreateRequestDto requestDto){
        Category category = categoryRepository.findById(requestDto.getCategoryId()).orElseThrow(()-> {throw new CustomException(ErrorCode.NOT_EXISTED_CATEGORY_ID);
        });

        User user = userRepository.findByKakaoId(requestDto.getKakaoId()).orElseThrow(()->{throw new CustomException(ErrorCode.NOT_EXISTED_USER_ID);});

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
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Post posts = postsRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        return id;
    }

    public PostGetResponseDto findById(Long id){
        Post post = postsRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_EXISTED_POST_ID));
        PostGetResponseDto postGetResponseDto = new PostGetResponseDto();
        postGetResponseDto.setCategoryName(post.getCategory().getName());
        postGetResponseDto.setNickname(post.getUser().getNickname());
        postGetResponseDto.setTitle(post.getTitle());
        postGetResponseDto.setSubTitle(post.getSub_title());
        postGetResponseDto.setContents(post.getContent());
        return postGetResponseDto;
    }

}
