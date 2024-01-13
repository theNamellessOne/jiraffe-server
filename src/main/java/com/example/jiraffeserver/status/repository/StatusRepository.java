package com.example.jiraffeserver.status.repository;

import com.example.jiraffeserver.common.repository.GenericEntityRepository;
import com.example.jiraffeserver.status.model.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends GenericEntityRepository<Status> {
    List<Status> findByBoardId(Long boardId);
}
