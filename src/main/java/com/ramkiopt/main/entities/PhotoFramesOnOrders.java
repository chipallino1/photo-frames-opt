package com.ramkiopt.main.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "photo_frames_on_orders", schema = "ramki_opt")
public class PhotoFramesOnOrders implements Identity {
    private Long id;
    private Long photoFrameId;
    private Integer photoFramesCount;
    private Long orderId;
    private PhotoFrames photoFramesByPhotoFrameId;
    private Orders ordersByOrderId;

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
    @Column(name = "photo_frame_id", nullable = false)
    public Long getPhotoFrameId() {
        return photoFrameId;
    }

    public void setPhotoFrameId(Long photoFrameId) {
        this.photoFrameId = photoFrameId;
    }

    @Basic
    @Column(name = "photo_frames_count", nullable = false)
    public Integer getPhotoFramesCount() {
        return photoFramesCount;
    }

    public void setPhotoFramesCount(Integer photoFramesCount) {
        this.photoFramesCount = photoFramesCount;
    }

    @Basic
    @Column(name = "order_id", nullable = false)
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoFramesOnOrders that = (PhotoFramesOnOrders) o;
        return id == that.id &&
                photoFrameId == that.photoFrameId &&
                photoFramesCount == that.photoFramesCount &&
                orderId == that.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoFrameId, photoFramesCount, orderId);
    }

    @ManyToOne
    @JoinColumn(name = "photo_frame_id", referencedColumnName = "id", nullable = false, insertable = false,
            updatable = false)
    public PhotoFrames getPhotoFramesByPhotoFrameId() {
        return photoFramesByPhotoFrameId;
    }

    public void setPhotoFramesByPhotoFrameId(PhotoFrames photoFramesByPhotoFrameId) {
        this.photoFramesByPhotoFrameId = photoFramesByPhotoFrameId;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Orders getOrdersByOrderId() {
        return ordersByOrderId;
    }

    public void setOrdersByOrderId(Orders ordersByOrderId) {
        this.ordersByOrderId = ordersByOrderId;
    }
}
