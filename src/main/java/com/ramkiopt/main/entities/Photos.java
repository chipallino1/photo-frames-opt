package com.ramkiopt.main.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Photos implements Identity {
    private Long id;
    private String photoSrc;
    private Integer width;
    private Integer height;
    private Long photoFrameId;
    private PhotoFrames photoFramesByPhotoFrameId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "photo_src", nullable = true, length = 1000)
    public String getPhotoSrc() {
        return photoSrc;
    }

    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }

    @Basic
    @Column(name = "width", nullable = true)
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Basic
    @Column(name = "height", nullable = true)
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Basic
    @Column(name = "photo_frame_id", nullable = false)
    public Long getPhotoFrameId() {
        return photoFrameId;
    }

    public void setPhotoFrameId(long photoFrameId) {
        this.photoFrameId = photoFrameId;
    }

    public void setPhotoFrameId(Long photoFrameId) {
        this.photoFrameId = photoFrameId;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Photos photos = (Photos) object;
        return Objects.equals(id, photos.id) &&
                Objects.equals(photoSrc, photos.photoSrc) &&
                Objects.equals(width, photos.width) &&
                Objects.equals(height, photos.height) &&
                Objects.equals(photoFrameId, photos.photoFrameId) &&
                Objects.equals(photoFramesByPhotoFrameId, photos.photoFramesByPhotoFrameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoSrc, width, height, photoFrameId, photoFramesByPhotoFrameId);
    }
}
