package com.example.jiraffeserver.common.mapper;

import com.example.jiraffeserver.common.model.GenericEntity;
import com.example.jiraffeserver.common.dto.GenericEntityDto;

import java.util.List;

public interface GenericEntityMapper<D extends GenericEntityDto, E extends GenericEntity> {
    D toDto(E entity);
    E toEntity(D dto);

    List<D> toDto(List<E> entity);
    List<E> toEntity(List<D> dto);
}