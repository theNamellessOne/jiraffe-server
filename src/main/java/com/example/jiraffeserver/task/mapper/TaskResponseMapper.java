package com.example.jiraffeserver.task.mapper;

import com.example.jiraffeserver.common.mapper.GenericEntityMapper;
import com.example.jiraffeserver.task.model.Task;
import com.example.jiraffeserver.task.dto.TaskResponseDto;
import org.mapstruct.Mapper;

@Mapper
public interface TaskResponseMapper extends
        GenericEntityMapper<TaskResponseDto, Task> {
}
