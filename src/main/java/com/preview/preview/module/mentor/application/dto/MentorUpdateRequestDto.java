package com.preview.preview.module.mentor.application.dto;

import com.preview.preview.module.job.domain.Job;
import com.preview.preview.module.job.domain.MentorJob;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class MentorUpdateRequestDto {
    private String nickname;
    private String contents;
    private Set<MentorJob> jobDtoSet;
}
