package com.preview.preview.module.user.application.dto;

import com.preview.preview.module.job.domain.Job;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserUpdateRequestDto {
    private Set<Job> jobDtoSet;
}
