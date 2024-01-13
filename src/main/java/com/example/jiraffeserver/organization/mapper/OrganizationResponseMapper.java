package com.example.jiraffeserver.organization.mapper;

import com.example.jiraffeserver.common.mapper.GenericEntityMapper;
import com.example.jiraffeserver.organization.dto.OrganizationResponseDto;
import com.example.jiraffeserver.organization.model.Organization;
import org.mapstruct.Mapper;

@Mapper
public interface OrganizationResponseMapper extends
        GenericEntityMapper<OrganizationResponseDto, Organization> {
}
