package com.example.jiraffeserver.task.controller;

import com.example.jiraffeserver.common.util.ServerResponse;
import com.example.jiraffeserver.common.util.ServerResponseRunner;
import com.example.jiraffeserver.task.dto.TaskRequestDto;
import com.example.jiraffeserver.task.dto.TaskResponseDto;
import com.example.jiraffeserver.task.request.TaskReorderRequest;
import com.example.jiraffeserver.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizations/{organizationId}/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("{taskId}")
    public ServerResponse<TaskResponseDto> find(@PathVariable Long taskId) {
        return ServerResponseRunner.safeRun(taskService::findById, taskId);
    }

    @PostMapping
    public ServerResponse<TaskResponseDto> save(@RequestBody TaskRequestDto request) {
        return ServerResponseRunner.safeRun(taskService::save, request);
    }

    @PatchMapping("reorder/{taskId}")
    public ServerResponse<TaskResponseDto> reorder(@PathVariable Long taskId,
                                                   @RequestBody TaskReorderRequest request) {
        request.setTaskId(taskId);
        return ServerResponseRunner.safeRun(taskService::reorder, request);
    }

    @DeleteMapping("{taskId}")
    public ServerResponse<TaskResponseDto> delete(@PathVariable Long taskId) {
        return ServerResponseRunner.safeRun(taskService::delete, taskId);
    }
}
