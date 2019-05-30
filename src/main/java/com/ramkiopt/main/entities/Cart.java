package com.ramkiopt.main.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Cart {
    private long id;
    private String name;
    private long clientId;
    private Date insertAt;
    private Users usersByClientId;
    private Collection<PhotoFramesOnCarts> photoFramesOnCartsById;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 300)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "client_id", nullable = false)
    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    @Basic
    @Column(name = "insert_at", nullable = true)
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
        Cart cart = (Cart) o;
        return id == cart.id &&
                clientId == cart.clientId &&
                Objects.equals(name, cart.name) &&
                Objects.equals(insertAt, cart.insertAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, clientId, insertAt);
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    public Users getUsersByClientId() {
        return usersByClientId;
    }

    public void setUsersByClientId(Users usersByClientId) {
        this.usersByClientId = usersByClientId;
    }

    @OneToMany(mappedBy = "cartByCartId")
    public Collection<PhotoFramesOnCarts> getPhotoFramesOnCartsById() {
        return photoFramesOnCartsById;
    }

    public void setPhotoFramesOnCartsById(Collection<PhotoFramesOnCarts> photoFramesOnCartsById) {
        this.photoFramesOnCartsById = photoFramesOnCartsById;
    }
}
