package com.preview.preview.module.form.application;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import com.preview.preview.module.form.application.dto.*;
import com.preview.preview.module.form.domain.Form;
import com.preview.preview.module.form.domain.FormRepository;
import com.preview.preview.module.form.domain.MentorForm;
import com.preview.preview.module.form.domain.MentorFormRepository;
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
    private final MentorFormRepository mentorFormRepository;
    private final UserRepository userRepository;

    public FormByMentorService(FormRepository formRepository, PostRepository postRepository, MentorFormRepository mentorFormRepository, UserRepository userRepository) {
        this.formRepository = formRepository;
        this.postRepository = postRepository;
        this.mentorFormRepository = mentorFormRepository;
        this.userRepository = userRepository;
    }


    @Transactional
    public List<MentorFormsByMentoGetResponseDto> getMemtorFormsByKakaoId(long kakaoId) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));

        List<MentorForm> forms = user.getMentorForms().stream().filter(form -> form.getStatus().equals("거절") == false).collect(Collectors.toList());

        List<MentorFormsByMentoGetResponseDto> formList = new ArrayList<>();

        for (MentorForm form : forms){
            formList.add(MentorFormsByMentoGetResponseDto.from(form));
        }
        return formList;
    }

    @Transactional
    public FormAcceptStatusResponseDto acceptForm(long formId, long kakaoId) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Form form = formRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));
        MentorForm mentorForm = mentorFormRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));

        if (user.getId() != mentorForm.getUser().getId()){
            throw new CustomException(ErrorCode.NOT_EQUAL_FORM_RESOURCE);
        }

        form.setStatus("수락");
        mentorForm.setStatus("수락");
        formRepository.save(form);
        mentorFormRepository.save(mentorForm);
        return FormAcceptStatusResponseDto.builder().status(form.getStatus()).build();
    }

    @Transactional
    public FormAcceptStatusResponseDto rejectForm(long formId, long kakaoId) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Form form = formRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));
        MentorForm mentorForm = mentorFormRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));

        if (user.getId() != mentorForm.getUser().getId()){
            throw new CustomException(ErrorCode.NOT_EQUAL_FORM_RESOURCE);
        }

        form.setDeleteTime();
        form.setStatus("거절");
        mentorForm.setDeleteTime();
        mentorForm.setStatus("거절");
        formRepository.save(form);
        mentorFormRepository.save(mentorForm);
        return FormAcceptStatusResponseDto.builder().status(form.getStatus()).build();
    }

    @Transactional
    public FormByMentorGetResponseDto getForm(long formId, long kakaoId){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Form form = formRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));
        MentorForm mentorForm = mentorFormRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));

        if (user.getId() != mentorForm.getUser().getId()){
            throw new CustomException(ErrorCode.NOT_EQUAL_FORM_RESOURCE);
        }
        return FormByMentorGetResponseDto.form(form);
    }

}
