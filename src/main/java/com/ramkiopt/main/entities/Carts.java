package com.ramkiopt.main.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Carts {
    private Long id;
    private String name;
    private Long clientId;
    private Date insertAt;
    private Users usersByClientId;
    private Collection<PhotoFramesOnCarts> photoFramesOnCartsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Basic
    @Column(name = "client_id")
    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "insert_at")
    public Date getInsertAt() {
        return insertAt;
    }

    public void setInsertAt(Date insertAt) {
        this.insertAt = insertAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carts carts = (Carts) o;
        return Objects.equals(id, carts.id) &&
                Objects.equals(name, carts.name) &&
                Objects.equals(clientId, carts.clientId) &&
                Objects.equals(insertAt, carts.insertAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, clientId, insertAt);
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false, insertable = false,
            updatable = false)
    public Users getUsersByClientId() {
        return usersByClientId;
    }

    public void setUsersByClientId(Users usersByClientId) {
        this.usersByClientId = usersByClientId;
    }

    @OneToMany(mappedBy = "cartsByCartId")
    public Collection<PhotoFramesOnCarts> getPhotoFramesOnCartsById() {
        return photoFramesOnCartsById;
    }

    public void setPhotoFramesOnCartsById(Collection<PhotoFramesOnCarts> photoFramesOnCartsById) {
        this.photoFramesOnCartsById = photoFramesOnCartsById;
    }
}
