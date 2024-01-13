package com.example.jiraffeserver.organization.model;

import com.example.jiraffeserver.board.model.Board;
import com.example.jiraffeserver.common.model.AuditingEntity;
import com.example.jiraffeserver.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@SuperBuilder
@RequiredArgsConstructor

@ToString(callSuper = true)

@Entity
public class Organization extends AuditingEntity {
    private String title;
    private String imageUrl;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "organization_id")
    private List<Board> boards;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "organization_members",
            joinColumns = @JoinColumn(name = "oragnization_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> members;
}
