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
public class FormByMentorService {

    private final FormRepository formRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public FormByMentorService(FormRepository formRepository, PostRepository postRepository, UserRepository userRepository) {
        this.formRepository = formRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public List<FormsByMentoGetResponseDto> getMemtorFormsByKakaoId(long kakaoId) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));

        List<Form> forms = user.getForms().stream().filter(form -> form.getStatus().equals("거절") == false).collect(Collectors.toList());

        List<FormsByMentoGetResponseDto> formList = new ArrayList<>();

        for (Form form : forms){
            formList.add(FormsByMentoGetResponseDto.from(form));
        }
        return formList;
    }

    @Transactional
    public FormAcceptStatusResponseDto acceptForm(long formId, long kakaoId) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Form form = formRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));

        if (user.getId() != form.getUser().getId()){
            throw new CustomException(ErrorCode.NOT_EQUAL_FORM_RESOURCE);
        }

        form.setStatus("수락");
        return FormAcceptStatusResponseDto.builder().status(form.getStatus()).build();
    }

    @Transactional
    public FormAcceptStatusResponseDto rejectForm(long formId, long kakaoId) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Form form = formRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));

        if (user.getId() != form.getUser().getId()){
            throw new CustomException(ErrorCode.NOT_EQUAL_FORM_RESOURCE);
        }

        form.setDeleteTime();
        form.setStatus("거절");
        return FormAcceptStatusResponseDto.builder().status(form.getStatus()).build();
    }

    @Transactional
    public FormByMentorGetResponseDto getForm(long formId, long kakaoId){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Form form = formRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));

        if (user.getId() != form.getUser().getId()){
            throw new CustomException(ErrorCode.NOT_EQUAL_FORM_RESOURCE);
        }
        return FormByMentorGetResponseDto.form(form);
    }

}
