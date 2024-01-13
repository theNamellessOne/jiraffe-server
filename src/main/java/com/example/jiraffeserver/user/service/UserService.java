package com.example.jiraffeserver.user.service;

import com.example.jiraffeserver.common.service.GenericEntityService;
import com.example.jiraffeserver.organization.repository.OrganizationRepository;
import com.example.jiraffeserver.organization.request.OrganizationPageableRequest;
import com.example.jiraffeserver.user.dto.UserResponseDto;
import com.example.jiraffeserver.user.dto.UserRequestDto;
import com.example.jiraffeserver.user.mapper.UserRequestMapper;
import com.example.jiraffeserver.user.mapper.UserResponseMapper;
import com.example.jiraffeserver.user.model.User;
import com.example.jiraffeserver.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService extends GenericEntityService<User, UserRequestDto, UserResponseDto> {
    private final OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository repository,
                       UserRequestMapper requestMapper,
                       UserResponseMapper responseMapper,
                       OrganizationRepository organizationRepository,
                       PasswordEncoder passwordEncoder) {
        super(repository, requestMapper, responseMapper);
        this.organizationRepository = organizationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<UserResponseDto> findOrganizationMembers(OrganizationPageableRequest request) {
        var organization = organizationRepository.findById(request.getOrganizationId()).orElseThrow();
        var members = ((UserRepository) repository)
                .findAllByOrganizationsIn(Set.of(organization), request.getPageable());

        return members.map(responseMapper::toDto);
    }

    public Optional<User> findByEmail(String email) {
        return ((UserRepository) repository).findByEmail(email);
    }

    @Override
    protected User preprocessEntitySave(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.preprocessEntitySave(entity);
    }
}
