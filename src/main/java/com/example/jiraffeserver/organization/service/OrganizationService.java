package com.example.jiraffeserver.organization.service;

import com.example.jiraffeserver.organization.dto.OrganizationResponseDto;
import com.example.jiraffeserver.organization.mapper.OrganizationRequestMapper;
import com.example.jiraffeserver.organization.request.OrganizationUpdateMembersRequest;
import com.example.jiraffeserver.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.jiraffeserver.auth.util.UserContext;
import com.example.jiraffeserver.organization.model.Organization;
import com.example.jiraffeserver.common.service.GenericEntityService;

import com.example.jiraffeserver.organization.dto.OrganizationRequestDto;

import com.example.jiraffeserver.organization.mapper.OrganizationResponseMapper;

import com.example.jiraffeserver.organization.repository.OrganizationRepository;

import java.util.Set;

@Service
public class OrganizationService
        extends GenericEntityService<Organization, OrganizationRequestDto, OrganizationResponseDto> {
    private final UserRepository userRepository;
    public OrganizationService(OrganizationRepository repository,
                               OrganizationRequestMapper requestMapper,
                               OrganizationResponseMapper responseMapper,
                               UserRepository userRepository) {
        super(repository, requestMapper, responseMapper);
        this.userRepository = userRepository;
    }

    public OrganizationResponseDto addMember(OrganizationUpdateMembersRequest request) {
        var user = userRepository.findByEmail(request.getMemberEmail()).orElseThrow();
        var organization = repository.findById(request.getOrganizationId()).orElseThrow();

        if (!user.getId().equals(organization.getCreatedBy().getId()))
            organization.getMembers().add(user);

        return responseMapper.toDto(repository.save(organization));
    }

    public OrganizationResponseDto removeMember(OrganizationUpdateMembersRequest request) {
        var user = userRepository.findByEmail(request.getMemberEmail()).orElseThrow();
        var organization = repository.findById(request.getOrganizationId()).orElseThrow();

        if (!user.getId().equals(organization.getCreatedBy().getId()))
            organization.getMembers().remove(user);

        return responseMapper.toDto(repository.save(organization));
    }

    public Page<OrganizationResponseDto> findMyOrganizations(Pageable pageable) {
        var currentUser = UserContext.getCurrentUser();
        var organizations = ((OrganizationRepository) repository)
                .findAllByMembersIn((Set.of(currentUser)), pageable);

        return organizations.map(responseMapper::toDto);
    }

    public Page<OrganizationResponseDto> findMyCreatedOrganizations(Pageable pageable) {
        var currentUser = UserContext.getCurrentUser();
        var organizations = ((OrganizationRepository) repository)
                .findAllByCreatedBy(currentUser, pageable);

        return organizations.map(responseMapper::toDto);
    }

    @Override
    protected Organization preprocessEntitySave(Organization entity) {
        if (entity.getId() == null) {
            var userEntity = userRepository.findById(UserContext.getCurrentUser().getId()).orElseThrow();

            entity.setCreatedBy(userEntity);
            entity.setMembers(Set.of(userEntity));
        } else {
            if(!entity.getCreatedBy().getId().equals(UserContext.getCurrentUser().getId()))
                throw new RuntimeException("Unauthorized");

            var entityExtracted = repository.findById(entity.getId()).orElseThrow();
            entity.setMembers(entityExtracted.getMembers());
        }

        return entity;
    }
}
