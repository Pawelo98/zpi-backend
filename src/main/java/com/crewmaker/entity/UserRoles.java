package com.crewmaker.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="UserRoles")
public class UserRoles {

    @EmbeddedId
    UserRolesId id;

    public UserRolesId getId() {
        return id;
    }

    public void setId(UserRolesId id) {
        this.id = id;
    }

    public UserRoles(UserRolesId id) {
        this.id = id;
    }

    public UserRoles() {
    }
}

@Embeddable
class UserRolesId implements Serializable {

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="UserPermittedID")
    private User userPermittedId;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="RoleAssignedID")
    private Role roleAssignedId;

    public UserRolesId(User userPermittedId, Role roleAssignedId) {
        this.userPermittedId = userPermittedId;
        this.roleAssignedId = roleAssignedId;
    }

    public UserRolesId() {
    }
}