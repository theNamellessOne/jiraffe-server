package com.example.jiraffeserver.status.mapper;
import com.example.jiraffeserver.board.mapper.BoardRequestMapper;
import com.example.jiraffeserver.common.mapper.GenericEntityMapper;
import com.example.jiraffeserver.status.dto.StatusRequestDto;
import com.example.jiraffeserver.status.model.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = BoardRequestMapper.class)
public interface StatusRequestMapper extends
        GenericEntityMapper<StatusRequestDto, Status> {
    @Override
    @Mapping(target = "board", source = "boardId")
    Status toEntity(StatusRequestDto dto);

    default Status fromId(Long id) {
        if (id == null) return null;

        return Status.builder().id(id).build();
    }
}
