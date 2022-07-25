package com.preview.preview.domain.web.dto.user;

import com.preview.preview.domain.job.Job;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserUpdateRequestDto {
    private Set<Job> jobDtoSet;
}
