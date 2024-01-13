package com.example.jiraffeserver.user.mapper;

import com.example.jiraffeserver.common.mapper.GenericEntityMapper;
import com.example.jiraffeserver.user.dto.UserRequestDto;
import com.example.jiraffeserver.user.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserRequestMapper extends GenericEntityMapper<UserRequestDto, User> {
}
