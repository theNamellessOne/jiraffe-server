package com.example.jiraffeserver.task.mapper;
import com.example.jiraffeserver.common.mapper.GenericEntityMapper;
import com.example.jiraffeserver.status.mapper.StatusRequestMapper;
import com.example.jiraffeserver.task.model.Task;
import com.example.jiraffeserver.task.dto.TaskRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = StatusRequestMapper.class)
public interface TaskRequestMapper extends
        GenericEntityMapper<TaskRequestDto, Task> {
    @Override
    @Mapping(target = "status", source = "statusId")
    Task toEntity(TaskRequestDto dto);
}
