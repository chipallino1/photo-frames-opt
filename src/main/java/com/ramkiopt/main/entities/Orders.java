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
public class Orders {
    private Long id;
    private Long clientId;
    private Timestamp orderDate;
    private String orderStatus;
    private Integer totalCost;
    private String comment;
    private String rowStatus;
    private Users usersByClientId;
    private Collection<PhotoFramesOnOrders> photoFramesOnOrdersById;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @Column(name = "order_date")
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "order_status")
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "total_cost")
    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "row_status")
    public String getRowStatus() {
        return rowStatus;
    }

    public void setRowStatus(String rowStatus) {
        this.rowStatus = rowStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id) &&
                Objects.equals(clientId, orders.clientId) &&
                Objects.equals(orderDate, orders.orderDate) &&
                Objects.equals(orderStatus, orders.orderStatus) &&
                Objects.equals(totalCost, orders.totalCost) &&
                Objects.equals(comment, orders.comment) &&
                Objects.equals(rowStatus, orders.rowStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, orderDate, orderStatus, totalCost, comment, rowStatus);
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    public Users getUsersByClientId() {
        return usersByClientId;
    }

    public void setUsersByClientId(Users usersByClientId) {
        this.usersByClientId = usersByClientId;
    }

    @OneToMany(mappedBy = "ordersByOrderId")
    public Collection<PhotoFramesOnOrders> getPhotoFramesOnOrdersById() {
        return photoFramesOnOrdersById;
    }

    public void setPhotoFramesOnOrdersById(Collection<PhotoFramesOnOrders> photoFramesOnOrdersById) {
        this.photoFramesOnOrdersById = photoFramesOnOrdersById;
    }
}
