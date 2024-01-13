package com.example.jiraffeserver.status.service;
import com.example.jiraffeserver.common.service.GenericEntityService;
import com.example.jiraffeserver.status.dto.StatusRequestDto;
import com.example.jiraffeserver.status.dto.StatusResponseDto;
import com.example.jiraffeserver.status.mapper.StatusRequestMapper;
import com.example.jiraffeserver.status.mapper.StatusResponseMapper;
import com.example.jiraffeserver.status.model.Status;
import com.example.jiraffeserver.status.repository.StatusRepository;
import com.example.jiraffeserver.status.request.StatusReorderRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService
        extends GenericEntityService<Status, StatusRequestDto, StatusResponseDto> {
    public StatusService(StatusRepository repository,
                         StatusRequestMapper requestMapper,
                         StatusResponseMapper responseMapper) {
        super(repository, requestMapper, responseMapper);
    }

    public StatusResponseDto reorder(StatusReorderRequest request) {
        var status = repository.findById(request.getStatusId()).orElseThrow();
        status.setPosition(request.getNewPosition());
        return responseMapper.toDto(repository.save(status));
    }

    public List<StatusResponseDto> findByBoardId(Long boardId) {
        return responseMapper.toDto(((StatusRepository) repository).findByBoardId(boardId));
    }
}
