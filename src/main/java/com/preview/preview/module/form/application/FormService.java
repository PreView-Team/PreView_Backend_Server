package com.preview.preview.module.form.application;

import com.preview.preview.core.exception.CustomException;
import com.preview.preview.core.exception.ErrorCode;
import com.preview.preview.module.form.application.dto.*;
import com.preview.preview.module.form.domain.Form;
import com.preview.preview.module.form.domain.FormRepository;
import com.preview.preview.module.form.domain.MentorForm;
import com.preview.preview.module.form.domain.MentorFormRepository;
import com.preview.preview.module.job.application.dto.JobDto;
import com.preview.preview.module.job.domain.Job;
import com.preview.preview.module.post.domain.Post;
import com.preview.preview.module.post.domain.PostRepository;
import com.preview.preview.module.user.domain.User;
import com.preview.preview.module.user.domain.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FormService{

    private final FormRepository formRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final MentorFormRepository mentorFormRepository;

    public FormService(FormRepository formRepository, PostRepository postRepository, UserRepository userRepository, MentorFormRepository mentorFormRepository) {
        this.formRepository = formRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.mentorFormRepository = mentorFormRepository;
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
                .mentorNicname(post.getUser().getMentor().getNickname())
                .status("대기")
                .name(formCreateRequestDto.getName())
                .content(formCreateRequestDto.getContents())
                .local(formCreateRequestDto.getLocal())
                .likeJobs(formCreateRequestDto.getJobNames())
                .phoneNumber(formCreateRequestDto.getPhoneNumber())
                .fcmToken(formCreateRequestDto.getFcmToken())
                .build();

        MentorForm mentorForm = MentorForm.builder()
                .user(post.getUser())
                .post(post)
                .status("대기")
                .name(formCreateRequestDto.getName())
                .content(formCreateRequestDto.getContents())
                .local(formCreateRequestDto.getLocal())
                .likedJobs(formCreateRequestDto.getJobNames())
                .fcmToken(formCreateRequestDto.getFcmToken())
                .phoneNumber(formCreateRequestDto.getPhoneNumber())
                .build();

        mentorFormRepository.save(mentorForm);

        return FormCreateResponseDto.from(formRepository.save(form));
    }

    @Transactional
    public FormGetResponseDto getForm(long kakaoId, long formId) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Form form = formRepository.findById(formId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));
        if (form.getUser().getId() != user.getId()) throw new CustomException(ErrorCode.NOT_EQUAL_FORM_RESOURCE);
        return FormGetResponseDto.from(form);
    }

    @Transactional
    public List<FormAllGetResponseDto> getFormsByKakaoId(long kakaoId) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));

        List<Form> forms = formRepository.findFormByuserId(user.getId());

        List<FormAllGetResponseDto> formList = new ArrayList<>();

        for (Form form : forms){
            formList.add(FormAllGetResponseDto.from(form));
        }
        return formList;
    }

    @Transactional
    public FormUpdateResponseDto formUpdate(long kakaoId, long formId, FormUpdateRequestDto formUpdateRequestDto) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Form form = formRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));
        MentorForm mentorForm = mentorFormRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));

        if (form.getUser().getId() != user.getId())
            throw new CustomException(ErrorCode.NOT_EQUAL_FORM_RESOURCE);

        form.setContent(formUpdateRequestDto.getContents());
        form.setPhoneNumber(formUpdateRequestDto.getPhoneNumber());
        form.setName(formUpdateRequestDto.getName());
        form.setLocal(formUpdateRequestDto.getLocal());
        form.setLikeJobs(formUpdateRequestDto.getJobNames());

        mentorForm.setContent(formUpdateRequestDto.getContents());
        mentorForm.setPhoneNumber(formUpdateRequestDto.getPhoneNumber());
        mentorForm.setName(formUpdateRequestDto.getName());
        mentorForm.setLocal(formUpdateRequestDto.getLocal());
        mentorForm.setLikedJobs(formUpdateRequestDto.getJobNames());

        mentorFormRepository.save(mentorForm);

        return FormUpdateResponseDto.from(formRepository.save(form));
    }

    @Transactional
    public FormDeleteResponseDto formDelete(long kakaoId, long formId){
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Form form = formRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));
        MentorForm mentorForm = mentorFormRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));

        if (user.getId() != form.getUser().getId()){
            throw new CustomException(ErrorCode.NOT_EQUAL_FORM_RESOURCE);
        }

        form.setDeleteTime();
        formRepository.delete(form);
        mentorForm.setDeleteTime();
        mentorFormRepository.delete(mentorForm);
        return FormDeleteResponseDto.builder().result("삭제 성공하였습니다.").build();
    }

    @Transactional
    public FormAcceptStatusResponseDto finishForm(long formId, long kakaoId) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Form form = formRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));
        MentorForm mentorForm = mentorFormRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));

        if (form.getUser().getId().equals(user.getId())){
            throw new CustomException(ErrorCode.NOT_EQUAL_FORM_RESOURCE);
        }

        form.setStatus("완료");
        mentorForm.setStatus("완료");
        formRepository.save(form);
        mentorFormRepository.save(mentorForm);
        return FormAcceptStatusResponseDto.builder().status(form.getStatus()).build();
    }

}
