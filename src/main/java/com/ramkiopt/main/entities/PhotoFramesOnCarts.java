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
@Table(name = "photo_frames_on_carts", schema = "ramki_opt", catalog = "")
public class PhotoFramesOnCarts {
    private Long id;
    private Long cartId;
    private Long photoFrameId;
    private Carts cartsByCartId;
    private PhotoFrames photoFramesByPhotoFrameId;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cart_id")
    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    @Basic
    @Column(name = "photo_frame_id")
    public Long getPhotoFrameId() {
        return photoFrameId;
    }

    public void setPhotoFrameId(Long photoFrameId) {
        this.photoFrameId = photoFrameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoFramesOnCarts that = (PhotoFramesOnCarts) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cartId, that.cartId) &&
                Objects.equals(photoFrameId, that.photoFrameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartId, photoFrameId);
    }

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    public Carts getCartsByCartId() {
        return cartsByCartId;
    }

    public void setCartsByCartId(Carts cartsByCartId) {
        this.cartsByCartId = cartsByCartId;
    }

    @ManyToOne
    @JoinColumn(name = "photo_frame_id", referencedColumnName = "id", nullable = false)
    public PhotoFrames getPhotoFramesByPhotoFrameId() {
        return photoFramesByPhotoFrameId;
    }

    public void setPhotoFramesByPhotoFrameId(PhotoFrames photoFramesByPhotoFrameId) {
        this.photoFramesByPhotoFrameId = photoFramesByPhotoFrameId;
    }
}
