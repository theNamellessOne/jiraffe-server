package com.example.jiraffeserver.common.dto;

import com.example.jiraffeserver.user.dto.UserResponseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;

import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
@SuperBuilder
@RequiredArgsConstructor
public abstract class GenericEntityDto {
    private Long id;
    private UserResponseDto createdBy;
    private UserResponseDto updatedBy;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
