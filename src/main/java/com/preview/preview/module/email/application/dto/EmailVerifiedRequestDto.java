package com.preview.preview.module.email.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EmailVerifiedRequestDto {
    String code;
}
