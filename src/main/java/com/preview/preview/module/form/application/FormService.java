package com.preview.preview.module.form.application;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import com.preview.preview.module.form.application.dto.*;
import com.preview.preview.module.form.domain.Form;
import com.preview.preview.module.form.domain.FormRepository;
import com.preview.preview.module.post.domain.Post;
import com.preview.preview.module.post.domain.PostRepository;
import com.preview.preview.module.user.domain.User;
import com.preview.preview.module.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormService{

    private final FormRepository formRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public FormService(FormRepository formRepository, PostRepository postRepository, UserRepository userRepository) {
        this.formRepository = formRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public FormCreateResponseDto createForm(long kakaoId, FormCreateRequestDto formCreateRequestDto) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Post post = postRepository.findById(formCreateRequestDto.getPostId()).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_POST_ID));

        if (formRepository.existsFormByUserIdAndPostId(user.getId(), post.getId())){
            throw new CustomException(ErrorCode.DUPLICATE_FORM_RESOURCE);
        }

        Form form = Form.builder()
                .user(user)
                .post(post)
                .name(formCreateRequestDto.getName())
                .content(formCreateRequestDto.getContext())
                .phoneNumber(formCreateRequestDto.getPhoneNumber())
                .build();

        return FormCreateResponseDto.from(formRepository.save(form));
    }

    @Transactional
    public FormGetResponseDto getForm(long kakaoId, long formId) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Form form = formRepository.findById(formId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));
        return FormGetResponseDto.from(form,user);
    }

    @Transactional
    public List<FormAllGetResponseDto> getFormsByKakaoId(long kakaoId) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));

        List<Form> forms = formRepository.findFormByuserId(user.getId()).stream().filter(form -> form.getDeletedDate() == null).collect(Collectors.toList());

        List<FormAllGetResponseDto> formList = new ArrayList<>();

        for (Form form : forms){
            formList.add(FormAllGetResponseDto.from(form));
        }
        return formList;
    }

    @Transactional
    public List<FormsByMentoGetResponseDto> getMemtoFormsByKakaoId(long kakaoId) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));

        List<Form> forms = user.getForms();

        List<FormsByMentoGetResponseDto> formList = new ArrayList<>();

        for (Form form : forms){
            formList.add(FormsByMentoGetResponseDto.from(form));
        }
        return formList;
    }

    @Transactional
    public FormUpdateResponseDto formUpdate(long kakaoId, long formId, FormUpdateRequestDto formUpdateRequestDto) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Form form = formRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));

        if (form.getUser().getId() != user.getId())
            throw new CustomException(ErrorCode.NOT_EQUAL_FORM_RESOURCE);

        form.setContent(formUpdateRequestDto.getContent());
        form.setPhoneNumber(formUpdateRequestDto.getPhoneNumber());
        form.setName(formUpdateRequestDto.getName());

        return FormUpdateResponseDto.from(formRepository.save(form));
    }

    @Transactional
    public FormDeleteResponseDto formDelete(long kakaoId, long formId){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Form form = formRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));

        if (user.getId() != form.getUser().getId()){
            throw new CustomException(ErrorCode.NOT_EQUAL_FORM_RESOURCE);
        }

        form.setDeleteTime();
        formRepository.save(form);
        return FormDeleteResponseDto.builder().result("삭제 성공하였습니다.").build();
    }

}
