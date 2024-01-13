package com.example.jiraffeserver.organization.mapper;
import com.example.jiraffeserver.common.mapper.GenericEntityMapper;
import com.example.jiraffeserver.organization.dto.OrganizationRequestDto;
import com.example.jiraffeserver.organization.model.Organization;
import com.example.jiraffeserver.organization.service.OrganizationService;
import org.mapstruct.Mapper;

@Mapper(uses = OrganizationService.class)
public interface OrganizationRequestMapper extends
        GenericEntityMapper<OrganizationRequestDto, Organization> {
    default Organization fromId(Long id) {
        if (id == null) return null;

        return Organization.builder().id(id).build();
    }
}
