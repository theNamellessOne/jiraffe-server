package com.example.jiraffeserver.organization.repository;

import com.example.jiraffeserver.common.repository.GenericEntityRepository;
import com.example.jiraffeserver.organization.model.Organization;
import com.example.jiraffeserver.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrganizationRepository extends GenericEntityRepository<Organization> {
    Page<Organization> findAllByCreatedBy(User creator, Pageable pageable);
    Page<Organization> findAllByMembersIn(Set<User> members, Pageable pageable);
}
