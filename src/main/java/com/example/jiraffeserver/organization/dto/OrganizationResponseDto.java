package com.example.jiraffeserver.organization.dto;

import com.example.jiraffeserver.user.dto.UserResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import com.example.jiraffeserver.common.dto.GenericEntityDto;

@Setter
@Getter
@SuperBuilder
@RequiredArgsConstructor

@ToString(callSuper = true)
public class OrganizationResponseDto extends GenericEntityDto {
    private String title;
    private String imageUrl;
}
