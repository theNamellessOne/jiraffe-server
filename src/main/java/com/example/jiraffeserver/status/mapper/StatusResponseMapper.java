package com.example.jiraffeserver.status.mapper;

import com.example.jiraffeserver.common.mapper.GenericEntityMapper;
import com.example.jiraffeserver.status.dto.StatusResponseDto;
import com.example.jiraffeserver.status.model.Status;
import org.mapstruct.Mapper;

@Mapper
public interface StatusResponseMapper extends
        GenericEntityMapper<StatusResponseDto, Status> {
}
