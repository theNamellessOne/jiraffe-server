package com.example.jiraffeserver.board.repository;

import com.example.jiraffeserver.board.model.Board;
import com.example.jiraffeserver.common.repository.GenericEntityRepository;
import com.example.jiraffeserver.organization.model.Organization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends GenericEntityRepository<Board> {
    Page<Board> findAllByOrganization(Organization organization, Pageable pageable);
}
