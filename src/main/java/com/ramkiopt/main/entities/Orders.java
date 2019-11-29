package com.ramkiopt.main.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Orders implements Identity {
    private Long id;
    private Timestamp orderDate;
    private String orderStatus;
    private String comment;
    private Users usersByUserId;
    private Collection<PhotoFramesOnOrders> photoFramesOnOrdersById;
    private Long userId;

    @Id
    @Column(name = "id", nullable = false)
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_date", nullable = false)
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "order_status", nullable = true, length = 100)
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "comment", nullable = true, length = 300)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id == orders.id &&
                Objects.equals(orderDate, orders.orderDate) &&
                Objects.equals(orderStatus, orders.orderStatus) &&
                Objects.equals(comment, orders.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, orderStatus, comment);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @OneToMany(mappedBy = "ordersByOrderId")
    public Collection<PhotoFramesOnOrders> getPhotoFramesOnOrdersById() {
        return photoFramesOnOrdersById;
    }

    public void setPhotoFramesOnOrdersById(Collection<PhotoFramesOnOrders> photoFramesOnOrdersById) {
        this.photoFramesOnOrdersById = photoFramesOnOrdersById;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
