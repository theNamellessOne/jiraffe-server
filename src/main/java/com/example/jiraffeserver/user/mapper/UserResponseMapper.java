package com.example.jiraffeserver.user.mapper;

import com.example.jiraffeserver.user.dto.UserResponseDto;
import org.mapstruct.Mapper;
import com.example.jiraffeserver.user.model.User;
import com.example.jiraffeserver.common.mapper.GenericEntityMapper;

@Mapper
public interface UserResponseMapper extends GenericEntityMapper<UserResponseDto, User> {
}
