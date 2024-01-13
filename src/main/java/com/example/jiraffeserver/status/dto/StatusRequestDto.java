package com.example.jiraffeserver.status.dto;

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
public class StatusRequestDto extends GenericEntityDto {
    private String title;
    private String imageUrl;
    private Long boardId;
}
