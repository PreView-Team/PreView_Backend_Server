package com.preview.preview.domain.web.dto.user;

import com.preview.preview.domain.job.Job;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.web.dto.authority.AuthorityDto;
import com.preview.preview.domain.web.dto.enterprise.EnterpriseDto;
import com.preview.preview.domain.web.dto.job.JobDto;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserUpdateRequestDto {
    private Set<Job> jobDtoSet;
}
