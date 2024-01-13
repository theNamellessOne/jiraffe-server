package com.example.jiraffeserver.board.controller;

import com.example.jiraffeserver.board.dto.BoardRequestDto;
import com.example.jiraffeserver.board.dto.BoardResponseDto;
import com.example.jiraffeserver.board.service.BoardService;
import com.example.jiraffeserver.common.util.ServerResponse;
import com.example.jiraffeserver.common.util.ServerResponseRunner;
import com.example.jiraffeserver.organization.util.OrganizationPageableRequestUtil;
import com.example.jiraffeserver.status.dto.StatusResponseDto;
import com.example.jiraffeserver.status.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations/{organizationId}/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final StatusService statusService;

    @GetMapping
    public ServerResponse<Page<BoardResponseDto>> findOrganizationBoards(@PathVariable Long organizationId, Pageable pageable) {
        return ServerResponseRunner.safeRun(boardService::findOrganizationBoards,
                OrganizationPageableRequestUtil.getRequest(organizationId, pageable));
    }

    @GetMapping("{boardId}")
    public ServerResponse<BoardResponseDto> findById(@PathVariable Long boardId) {
        return ServerResponseRunner.safeRun(boardService::findById, boardId);
    }

    @GetMapping("{boardId}/statuses")
    public ServerResponse<List<StatusResponseDto>> findBoardStatuses(@PathVariable Long boardId) {
        return ServerResponseRunner.safeRun(statusService::findByBoardId, boardId);
    }

    @PostMapping
    public ServerResponse<BoardResponseDto> save(@RequestBody BoardRequestDto request) {
        return ServerResponseRunner.safeRun(boardService::save, request);
    }

    @DeleteMapping("{boardId}")
    public ServerResponse<BoardResponseDto> delete(@PathVariable Long boardId) {
        return ServerResponseRunner.safeRun(boardService::delete, boardId);
    }
}
