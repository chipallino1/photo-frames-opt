package com.ramkiopt.main.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Roles {
    private long id;
    private String name;
    private Collection<UsersOnRoles> usersOnRolesById;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return id == roles.id &&
                Objects.equals(name, roles.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "rolesByRoleId")
    public Collection<UsersOnRoles> getUsersOnRolesById() {
        return usersOnRolesById;
    }

    public void setUsersOnRolesById(Collection<UsersOnRoles> usersOnRolesById) {
        this.usersOnRolesById = usersOnRolesById;
    }
}
