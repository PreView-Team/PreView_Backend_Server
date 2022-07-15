package com.preview.preview.domain.web.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaildedNicknameDto {
    private Boolean isValidNickname;
}
