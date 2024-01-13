package com.example.jiraffeserver.common.model;
import com.example.jiraffeserver.common.configuration.AuditListener;
import com.example.jiraffeserver.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@ToString
@SuperBuilder
@RequiredArgsConstructor

@MappedSuperclass
@EntityListeners(AuditListener.class)
public abstract class AuditingEntity extends GenericEntity {
    @ManyToOne
    @JoinColumn(name = "created_by", updatable = false)
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;
}
