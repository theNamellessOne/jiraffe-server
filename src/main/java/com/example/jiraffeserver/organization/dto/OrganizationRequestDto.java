package com.example.jiraffeserver.organization.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;

import lombok.experimental.SuperBuilder;

import com.example.jiraffeserver.common.dto.GenericEntityDto;

@Setter
@Getter
@SuperBuilder
@RequiredArgsConstructor

@ToString(callSuper = true)
public class OrganizationRequestDto extends GenericEntityDto {
    private String title;
    private String imageUrl;
}
