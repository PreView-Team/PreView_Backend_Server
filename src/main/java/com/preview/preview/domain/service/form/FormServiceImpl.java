package com.preview.preview.domain.service.form;

import com.preview.preview.domain.exception.CustomException;
import com.preview.preview.domain.exception.ErrorCode;
import com.preview.preview.domain.form.Form;
import com.preview.preview.domain.form.FormRepository;
import com.preview.preview.domain.post.Post;
import com.preview.preview.domain.post.PostRepository;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.user.UserRepository;
import com.preview.preview.domain.web.dto.form.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        return FormCreateResponseDto.from(formRepository.save(form));
    }

    @Override
    public FormGetResponseDto getForm(long formId) {
        Form form = formRepository.findById(formId).orElseThrow(()-> new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));
        return FormGetResponseDto.from(form);
    }

    @Override
    public List<FormAllGetResponseDto> getFormsByKakaoId(long kakaoId) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));

        List<Form> forms = formRepository.findFormByuserId(user.getId()).stream().filter(form -> form.getDeletedDate() == null).collect(Collectors.toList());

        List<FormAllGetResponseDto> formList = new ArrayList<>();

        for (Form form : forms){
            formList.add(FormAllGetResponseDto.from(form));
        }
        return formList;
    }

    @Override
    public FormUpdateResponseDto getUpdate(long kakaoId, long formId, FormUpdateRequestDto formUpdateRequestDto) {
        User user = userRepository.findByKakaoId(kakaoId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_USER_ID));
        Form form = formRepository.findById(formId).orElseThrow(()->new CustomException(ErrorCode.NOT_EXISTED_FORM_ID));

        if (form.getUser().getId() != user.getId())
            throw new CustomException(ErrorCode.NOT_EQUAL_FORM_RESOURCE);

        form.setContext(formUpdateRequestDto.getContext());
        form.setPhoneNumber(formUpdateRequestDto.getPhoneNumber());
        form.setWantedJob(formUpdateRequestDto.getWantedJob());
        form.setName(formUpdateRequestDto.getName());
        form.setUniversity(formUpdateRequestDto.getUniversity());

        return FormUpdateResponseDto.from(formRepository.save(form));
    }
}
