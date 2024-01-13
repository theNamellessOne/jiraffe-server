package com.example.jiraffeserver.organization.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@RequiredArgsConstructor

@ToString(callSuper = true)
public class OrganizationUpdateMembersRequest {
    private Long organizationId;
    private String memberEmail;
}
