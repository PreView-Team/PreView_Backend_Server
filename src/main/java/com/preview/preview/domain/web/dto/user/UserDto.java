package com.preview.preview.domain.web.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.preview.preview.domain.enterprise.Enterprise;
import com.preview.preview.domain.user.User;
import com.preview.preview.domain.web.dto.AuthorityDto;
import com.preview.preview.domain.web.dto.enterprise.EnterpriseDto;
import com.preview.preview.domain.web.dto.job.JobDto;
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
