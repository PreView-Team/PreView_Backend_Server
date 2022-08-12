package com.preview.preview.module.user.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.preview.preview.module.auth.application.dto.AuthorityDto;
import com.preview.preview.module.enterprise.application.dto.EnterpriseDto;
import com.preview.preview.module.job.application.dto.JobDto;
import com.preview.preview.module.user.domain.User;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;

    @NotNull
    @Size(min = 3, max = 50)
    private String nickname;

    @NotNull
    private long kakaoId;

    private String email;

    private Set<JobDto> jobDtoSet;

    private Set<EnterpriseDto> enterpriseDtoSet;

    private Set<AuthorityDto> authorityDtoSet;

    private boolean activated;

    public static UserDto from(User user){
        if (user == null) return null;
        return UserDto.builder()
                .nickname(user.getNickname())
                .kakaoId(user.getKakaoId())
                .email(user.getEmail())
                .jobDtoSet(user.getLikedJobs().stream()
                        .map(job -> JobDto.builder().jobName(job.getName()).build())
                        .collect(Collectors.toSet()))
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
