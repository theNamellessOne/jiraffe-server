package com.example.jiraffeserver.user.repository;

import com.example.jiraffeserver.organization.model.Organization;
import com.example.jiraffeserver.user.model.User;
import com.example.jiraffeserver.common.repository.GenericEntityRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends GenericEntityRepository<User> {
    Page<User> findAllByOrganizationsIn(Set<Organization> organizations, Pageable pageable);
    Optional<User> findByEmail(String email);
}
