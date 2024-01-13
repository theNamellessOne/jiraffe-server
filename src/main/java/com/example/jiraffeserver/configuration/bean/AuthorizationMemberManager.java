package com.example.jiraffeserver.configuration.bean;

import com.example.jiraffeserver.auth.util.UserContext;
import com.example.jiraffeserver.organization.repository.OrganizationRepository;
import com.example.jiraffeserver.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class AuthorizationMemberManager implements AuthorizationManager<RequestAuthorizationContext> {
    private final OrganizationRepository organizationRepository;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
        if(!authentication.get().isAuthenticated()) return new AuthorizationDecision(false);

        var organizationId = Long.parseLong(context.getVariables().get("organizationId"));
        var organization = organizationRepository.findById(organizationId).orElse(null);

        if (organization == null) return new AuthorizationDecision(false);

        var currentUserId = UserContext.getCurrentUser().getId();
        boolean canAccess = false;
        for (User member : organization.getMembers()) {
            if (member.getId().equals(currentUserId)) {
                canAccess = true;
                break;
            }
        }

        return new AuthorizationDecision(canAccess);
    }
}
