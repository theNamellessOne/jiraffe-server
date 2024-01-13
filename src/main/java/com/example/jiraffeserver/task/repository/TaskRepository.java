package com.example.jiraffeserver.task.repository;

import com.example.jiraffeserver.common.repository.GenericEntityRepository;
import com.example.jiraffeserver.task.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends GenericEntityRepository<Task> {
    List<Task> findByStatusId(Long statusId);
}
