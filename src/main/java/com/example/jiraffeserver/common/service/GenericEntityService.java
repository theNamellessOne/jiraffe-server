package com.example.jiraffeserver.common.service;

import com.example.jiraffeserver.common.dto.GenericEntityDto;
import com.example.jiraffeserver.common.mapper.GenericEntityMapper;
import com.example.jiraffeserver.common.model.GenericEntity;
import com.example.jiraffeserver.common.repository.GenericEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public abstract class GenericEntityService<Entity extends GenericEntity, RequestDto extends GenericEntityDto, ResponseDto extends GenericEntityDto> {
    protected final GenericEntityRepository<Entity> repository;

    protected final GenericEntityMapper<RequestDto, Entity> requestMapper;
    protected final GenericEntityMapper<ResponseDto, Entity> responseMapper;

    public GenericEntityService(GenericEntityRepository<Entity> repository,
                                GenericEntityMapper<RequestDto, Entity> requestMapper,
                                GenericEntityMapper<ResponseDto, Entity> responseMapper) {
        this.repository = repository;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    public ResponseDto findById(Long id) {
        var entity = repository.findById(id).orElseThrow();
        return responseMapper.toDto(entity);
    }

    public Page<ResponseDto> findAll(Pageable pageable) {
        var entities = repository.findAll(pageable);
        return entities.map(responseMapper::toDto);
    }

    public ResponseDto save(RequestDto requestDto) {
        requestDto = preprocessRequestDtoSave(requestDto);

        var entity = requestMapper.toEntity(requestDto);
        entity = preprocessEntitySave(entity);

        return responseMapper.toDto(repository.save(entity));
    }

    public ResponseDto delete(Long id) {
        var entity = repository.findById(id).orElseThrow();

        repository.deleteById(id);

        return responseMapper.toDto(entity);
    }

    protected RequestDto preprocessRequestDtoSave(RequestDto requestDto) {
        return requestDto;
    }

    protected Entity preprocessEntitySave(Entity entity) {
       return entity;
    }
}
