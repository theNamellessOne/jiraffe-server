package com.example.jiraffeserver.task.service;
import com.example.jiraffeserver.common.service.GenericEntityService;
import com.example.jiraffeserver.task.model.Task;
import com.example.jiraffeserver.task.dto.TaskRequestDto;
import com.example.jiraffeserver.task.dto.TaskResponseDto;
import com.example.jiraffeserver.task.mapper.TaskRequestMapper;
import com.example.jiraffeserver.task.mapper.TaskResponseMapper;
import com.example.jiraffeserver.task.repository.TaskRepository;
import com.example.jiraffeserver.task.request.TaskReorderRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService
        extends GenericEntityService<Task, TaskRequestDto, TaskResponseDto> {
    public TaskService(TaskRepository repository,
                       TaskRequestMapper requestMapper,
                       TaskResponseMapper responseMapper) {
        super(repository, requestMapper, responseMapper);
    }

    public TaskResponseDto reorder(TaskReorderRequest request) {
        var task = repository.findById(request.getTaskId()).orElseThrow();
        task.setPosition(request.getNewPosition());
        return responseMapper.toDto(repository.save(task));
    }

    public List<TaskResponseDto> findByStatusId(Long statusId) {
        return responseMapper.toDto(((TaskRepository) repository).findByStatusId(statusId));
    }
}
