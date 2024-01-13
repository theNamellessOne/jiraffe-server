package com.example.jiraffeserver.task.request;

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
public class TaskReorderRequest {
    private Long taskId;
    private Integer newPosition;
}
