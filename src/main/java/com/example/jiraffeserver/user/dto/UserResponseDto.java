package com.example.jiraffeserver.user.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import com.example.jiraffeserver.common.dto.GenericEntityDto;

@Setter
@Getter
@SuperBuilder
@RequiredArgsConstructor

@ToString(callSuper = true)
public class UserResponseDto extends GenericEntityDto {
    private String name;
    private String surname;

    private String email;
    private String imageUrl;
}
