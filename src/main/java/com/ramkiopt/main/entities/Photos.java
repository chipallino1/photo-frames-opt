package com.ramkiopt.main.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Photos {
    private Long id;
    private String photoSrc;
    private String size;
    private Long photoFrameId;
    private Collection<PhotoFramesOnPhotos> photoFramesOnPhotosById;
    private PhotoFrames photoFramesByPhotoFrameId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
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
    @Column(name = "size", nullable = true, length = 100)
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Basic
    @Column(name = "photo_frame_id", nullable = false)
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
        Photos photos = (Photos) o;
        return id == photos.id &&
                photoFrameId == photos.photoFrameId &&
                Objects.equals(photoSrc, photos.photoSrc) &&
                Objects.equals(size, photos.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoSrc, size, photoFrameId);
    }

    @OneToMany(mappedBy = "photosByPhotoId")
    public Collection<PhotoFramesOnPhotos> getPhotoFramesOnPhotosById() {
        return photoFramesOnPhotosById;
    }

    public void setPhotoFramesOnPhotosById(Collection<PhotoFramesOnPhotos> photoFramesOnPhotosById) {
        this.photoFramesOnPhotosById = photoFramesOnPhotosById;
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
}
