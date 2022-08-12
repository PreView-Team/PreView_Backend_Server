package com.preview.preview.module.user.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaildedNicknameDto {
    private Boolean isValidNickname;

    public static VaildedNicknameDto from(boolean isChecked){
        return VaildedNicknameDto.builder().isValidNickname(isChecked).build();
    }
}
