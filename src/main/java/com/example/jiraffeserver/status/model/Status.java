package com.example.jiraffeserver.status.model;

import com.example.jiraffeserver.board.model.Board;
import com.example.jiraffeserver.common.model.AuditingEntity;
import com.example.jiraffeserver.common.model.GenericEntity;
import com.example.jiraffeserver.task.model.Task;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

@Entity
public class Status extends AuditingEntity {
    private String title;
    private String description;

    private Integer position;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task tasks;
}
