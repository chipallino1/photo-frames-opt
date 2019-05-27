package com.ramkiopt.main.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photo_frames_on_carts", schema = "ramki_opt", catalog = "")
public class PhotoFramesOnCarts {
    private long id;
    private long cartId;
    private long photoFrameId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cart_id", nullable = false)
    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    @Basic
    @Column(name = "photo_frame_id", nullable = false)
    public long getPhotoFrameId() {
        return photoFrameId;
    }

    public void setPhotoFrameId(long photoFrameId) {
        this.photoFrameId = photoFrameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoFramesOnCarts that = (PhotoFramesOnCarts) o;
        return id == that.id &&
                cartId == that.cartId &&
                photoFrameId == that.photoFrameId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartId, photoFrameId);
    }
}
