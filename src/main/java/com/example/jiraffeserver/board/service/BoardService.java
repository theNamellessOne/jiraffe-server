package com.example.jiraffeserver.board.service;

import com.example.jiraffeserver.board.dto.BoardRequestDto;
import com.example.jiraffeserver.board.dto.BoardResponseDto;
import com.example.jiraffeserver.board.mapper.BoardRequestMapper;
import com.example.jiraffeserver.board.mapper.BoardResponseMapper;
import com.example.jiraffeserver.board.model.Board;
import com.example.jiraffeserver.board.repository.BoardRepository;
import com.example.jiraffeserver.common.service.GenericEntityService;
import com.example.jiraffeserver.organization.repository.OrganizationRepository;
import com.example.jiraffeserver.organization.request.OrganizationPageableRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class BoardService
        extends GenericEntityService<Board, BoardRequestDto, BoardResponseDto> {
    private final OrganizationRepository organizationRepository;
    public BoardService(BoardRepository repository,
                        BoardRequestMapper requestMapper,
                        BoardResponseMapper responseMapper,
                        OrganizationRepository organizationRepository) {
        super(repository, requestMapper, responseMapper);
        this.organizationRepository = organizationRepository;
    }

    public Page<BoardResponseDto> findOrganizationBoards(OrganizationPageableRequest request) {
        var organization = organizationRepository.findById(request.getOrganizationId()).orElseThrow();
        var boards = ((BoardRepository) request).findAllByOrganization(organization, request.getPageable());

        return boards.map(responseMapper::toDto);
    }
}
