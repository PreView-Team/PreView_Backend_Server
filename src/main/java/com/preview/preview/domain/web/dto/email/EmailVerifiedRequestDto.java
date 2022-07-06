package com.preview.preview.domain.web.dto.email;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EmailVerifiedRequestDto {
    String code;
}
