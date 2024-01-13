package com.example.jiraffeserver.user.dto;

import com.example.jiraffeserver.common.dto.GenericEntityDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@RequiredArgsConstructor

@ToString(callSuper = true)
public class UserRequestDto extends GenericEntityDto {
    private String name;
    private String surname;

    private String email;
    private String imageUrl;
    private String password;
}
