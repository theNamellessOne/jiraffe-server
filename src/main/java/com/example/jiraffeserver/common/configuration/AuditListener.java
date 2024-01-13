package com.example.jiraffeserver.common.configuration;

import com.example.jiraffeserver.auth.util.UserContext;
import com.example.jiraffeserver.common.model.AuditingEntity;
import com.example.jiraffeserver.user.repository.UserRepository;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class AuditListener {
    @PrePersist
    private void beforeAnyCreate(AuditingEntity auditingEntity) {
        var user = UserContext.getCurrentUser();
        auditingEntity.setCreatedBy(user);
    }

    @PreUpdate
    private void beforeAnyUpdate(AuditingEntity auditingEntity) {
        var user = UserContext.getCurrentUser();
        auditingEntity.setUpdatedBy(user);
    }
}
