package com.example.jiraffeserver.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.jiraffeserver.common.model.GenericEntity;

public interface GenericEntityRepository<T extends GenericEntity> extends JpaRepository<T, Long> {
}
