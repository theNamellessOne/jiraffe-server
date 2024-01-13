package com.example.jiraffeserver.board.mapper;
import com.example.jiraffeserver.board.dto.BoardRequestDto;
import com.example.jiraffeserver.board.model.Board;
import com.example.jiraffeserver.common.mapper.GenericEntityMapper;
import com.example.jiraffeserver.organization.mapper.OrganizationRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = OrganizationRequestMapper.class)
public interface BoardRequestMapper extends
        GenericEntityMapper<BoardRequestDto, Board> {
    @Override
    @Mapping(target = "organization", source = "organizationId")
    Board toEntity(BoardRequestDto dto);

    default Board fromId(Long id) {
        if (id == null) return null;

        return Board.builder().id(id).build();
    }
}
