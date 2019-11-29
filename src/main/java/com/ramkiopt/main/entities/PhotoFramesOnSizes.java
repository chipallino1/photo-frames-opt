package com.ramkiopt.main.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photo_frames_on_sizes", schema = "ramki_opt", catalog = "")
public class PhotoFramesOnSizes implements Identity {
    private Long id;
    private Long photoFrameId;
    private Long sizeId;
    private PhotoFrames photoFramesByPhotoFrameId;
    private Sizes sizesBySizeId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Basic
    @Column(name = "size_id", nullable = false)
    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(long sizeId) {
        this.sizeId = sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoFramesOnSizes that = (PhotoFramesOnSizes) o;
        return id == that.id &&
                photoFrameId == that.photoFrameId &&
                sizeId == that.sizeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoFrameId, sizeId);
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
    @JoinColumn(name = "size_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Sizes getSizesBySizeId() {
        return sizesBySizeId;
    }

    public void setSizesBySizeId(Sizes sizesBySizeId) {
        this.sizesBySizeId = sizesBySizeId;
    }
}
