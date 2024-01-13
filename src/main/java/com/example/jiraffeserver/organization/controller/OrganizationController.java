package com.example.jiraffeserver.organization.controller;

import com.example.jiraffeserver.common.util.ServerResponse;
import com.example.jiraffeserver.common.util.ServerResponseRunner;
import com.example.jiraffeserver.organization.dto.OrganizationRequestDto;
import com.example.jiraffeserver.organization.dto.OrganizationResponseDto;
import com.example.jiraffeserver.organization.request.OrganizationUpdateMembersRequest;
import com.example.jiraffeserver.organization.service.OrganizationService;
import com.example.jiraffeserver.organization.util.OrganizationPageableRequestUtil;
import com.example.jiraffeserver.user.dto.UserResponseDto;
import com.example.jiraffeserver.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final UserService userService;
    private final OrganizationService organizationService;

    @GetMapping("my")
    public ServerResponse<Page<OrganizationResponseDto>> findMyOrganizations(Pageable pageable) {
        return ServerResponseRunner.safeRun(organizationService::findMyOrganizations, pageable);
    }

    @GetMapping("my/created")
    public ServerResponse<Page<OrganizationResponseDto>> findMyCreatedOrganizations(Pageable pageable) {
        return ServerResponseRunner.safeRun(organizationService::findMyCreatedOrganizations, pageable);
    }

    @GetMapping("{organizationId}/members")
    public ServerResponse<Page<UserResponseDto>> getOrganizationMembers(@PathVariable Long organizationId,
                                                                        Pageable pageable) {
        return ServerResponseRunner.safeRun(userService::findOrganizationMembers,
                OrganizationPageableRequestUtil.getRequest(organizationId, pageable));
    }

    @PatchMapping("{organizationId}/members/add/{email}")
    public ServerResponse<OrganizationResponseDto> addMember(@PathVariable Long organizationId,
                                                             @PathVariable String email) {
        return ServerResponseRunner.safeRun(
                organizationService::addMember,
                OrganizationUpdateMembersRequest
                        .builder()
                        .organizationId(organizationId)
                        .memberEmail(email)
                        .build()
        );
    }

    @PatchMapping("{organizationId}/members/remove/{email}")
    public ServerResponse<OrganizationResponseDto> removeMember(@PathVariable Long organizationId,
                                                                @PathVariable String email) {
        return ServerResponseRunner.safeRun(
                organizationService::removeMember,
                OrganizationUpdateMembersRequest
                        .builder()
                        .organizationId(organizationId)
                        .memberEmail(email)
                        .build()
                );
    }

    @PatchMapping("{organizationId}")
    public ServerResponse<OrganizationResponseDto> edit(@PathVariable Long organizationId,
                                                        @RequestBody OrganizationRequestDto request) {
        request.setId(organizationId);
        return ServerResponseRunner.safeRun(organizationService::save, request);
    }


    @PutMapping
    public ServerResponse<OrganizationResponseDto> save(@RequestBody OrganizationRequestDto request) {
        request.setId(null);
        return ServerResponseRunner.safeRun(organizationService::save, request);
    }

    @DeleteMapping("{organizationId}")
    public ServerResponse<OrganizationResponseDto> delete(@PathVariable Long organizationId) {
        return ServerResponseRunner.safeRun(organizationService::delete, organizationId);
    }
}
