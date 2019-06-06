package com.ramkiopt.main.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photo_frames_on_photos", schema = "ramki_opt", catalog = "")
public class PhotoFramesOnPhotos {
    private Long id;
    private Long photoFrameId;
    private Long photoId;
    private PhotoFrames photoFramesByPhotoFrameId;
    private Photos photosByPhotoId;

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
    @Column(name = "photo_frame_id", nullable = false)
    public Long getPhotoFrameId() {
        return photoFrameId;
    }

    public void setPhotoFrameId(Long photoFrameId) {
        this.photoFrameId = photoFrameId;
    }

    @Basic
    @Column(name = "photo_id", nullable = false)
    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoFramesOnPhotos that = (PhotoFramesOnPhotos) o;
        return id == that.id &&
                photoFrameId == that.photoFrameId &&
                photoId == that.photoId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoFrameId, photoId);
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
    @JoinColumn(name = "photo_id", referencedColumnName = "id", nullable = false, insertable = false,
            updatable = false)
    public Photos getPhotosByPhotoId() {
        return photosByPhotoId;
    }

    public void setPhotosByPhotoId(Photos photosByPhotoId) {
        this.photosByPhotoId = photosByPhotoId;
    }
}
