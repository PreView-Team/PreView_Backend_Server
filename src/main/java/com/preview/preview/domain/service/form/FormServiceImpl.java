package com.preview.preview.domain.service.form;

import com.preview.preview.domain.exception.CustomException;
import com.preview.preview.domain.exception.ErrorCode;
import com.preview.preview.domain.form.Form;
import com.preview.preview.domain.form.FormRepository;
import com.preview.preview.domain.post.Post;
import com.preview.preview.domain.post.PostRepository;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.user.UserRepository;
import com.preview.preview.domain.web.dto.form.FormCreateRequestDto;
import com.preview.preview.domain.web.dto.form.FormCreateResponseDto;
import com.preview.preview.domain.web.dto.form.FormDto;
import com.preview.preview.domain.web.dto.form.FormGetResponseDto;
import org.springframework.stereotype.Service;

@Service
public class FormServiceImpl implements FormSevice{

    private final FormRepository formRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public FormServiceImpl(FormRepository formRepository, PostRepository postRepository, UserRepository userRepository) {
        this.formRepository = formRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public FormCreateResponseDto createForm(long kakaoId, FormCreateRequestDto formCreateRequestDto) {
        FormCreateResponseDto formCreateResponseDto = new FormCreateResponseDto();
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Post post = postRepository.findById(formCreateRequestDto.getPostId()).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_POST_ID));

        if (formRepository.existsFormByUserIdAndPostId(user.getId(), post.getId())){
            throw new CustomException(ErrorCode.DUPLICATE_FORM_RESOURCE);
        }
        Form form = Form.builder()
                .user(user)
                .post(post)
                .name(formCreateRequestDto.getName())
                .context(formCreateRequestDto.getContext())
                .university(formCreateRequestDto.getUniversity())
                .phoneNumber(formCreateRequestDto.getPhoneNumber())
                .wantedJob(formCreateRequestDto.getWantedJob())
                .build();

        FormDto.from(formRepository.save(form));
        formCreateResponseDto.setResult("멘티 신청 완료 되었습니다.");
        formCreateResponseDto.setId(form.getId());
        return formCreateResponseDto;
    }

    @Override
    public FormGetResponseDto getForm(long formId) {
        Form form = formRepository.findById(formId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));
        return FormGetResponseDto.from(form);
    }
}
