package com.example.jiraffeserver.status.controller;

import com.example.jiraffeserver.common.util.ServerResponse;
import com.example.jiraffeserver.common.util.ServerResponseRunner;
import com.example.jiraffeserver.status.dto.StatusRequestDto;
import com.example.jiraffeserver.status.dto.StatusResponseDto;
import com.example.jiraffeserver.status.request.StatusReorderRequest;
import com.example.jiraffeserver.status.service.StatusService;
import com.example.jiraffeserver.task.dto.TaskResponseDto;
import com.example.jiraffeserver.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations/{organizationId}/statuses")
@RequiredArgsConstructor
public class StatusController {
    private final TaskService taskService;
    private final StatusService statusService;

    @GetMapping("{statusId}")
    public ServerResponse<StatusResponseDto> find(@PathVariable Long statusId) {
        return ServerResponseRunner.safeRun(statusService::findById, statusId);
    }

    @GetMapping("{statusId}/tasks")
    public ServerResponse<List<TaskResponseDto>> findTasksByStatus(@PathVariable Long statusId) {
        return ServerResponseRunner.safeRun(taskService::findByStatusId, statusId);
    }

    @PostMapping
    public ServerResponse<StatusResponseDto> save(@RequestBody StatusRequestDto request) {
        return ServerResponseRunner.safeRun(statusService::save, request);
    }

    @PatchMapping("reorder/{statusId}")
    public ServerResponse<StatusResponseDto> reorder(@PathVariable Long statusId,
                                                     @RequestBody StatusReorderRequest request) {
        request.setStatusId(statusId);
        return ServerResponseRunner.safeRun(statusService::reorder, request);
    }

    @DeleteMapping("{statusId}")
    public ServerResponse<StatusResponseDto> delete(@PathVariable Long statusId) {
        return ServerResponseRunner.safeRun(statusService::delete, statusId);
    }
}
