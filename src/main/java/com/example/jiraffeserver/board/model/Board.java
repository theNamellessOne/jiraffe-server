package com.example.jiraffeserver.board.model;

import com.example.jiraffeserver.common.model.AuditingEntity;
import com.example.jiraffeserver.common.model.GenericEntity;
import com.example.jiraffeserver.organization.model.Organization;
import com.example.jiraffeserver.status.model.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Setter
@Getter
@SuperBuilder
@RequiredArgsConstructor

@ToString(callSuper = true)

@Entity
public class Board extends AuditingEntity {
    private String title;
    private String imageUrl;

    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "board_id")
    private List<Status> statuses;


    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
