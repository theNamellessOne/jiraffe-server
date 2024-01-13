package com.example.jiraffeserver.board.mapper;

import com.example.jiraffeserver.board.dto.BoardResponseDto;
import com.example.jiraffeserver.board.model.Board;
import com.example.jiraffeserver.common.mapper.GenericEntityMapper;
import org.mapstruct.Mapper;

@Mapper
public interface BoardResponseMapper extends
        GenericEntityMapper<BoardResponseDto, Board> {
}
