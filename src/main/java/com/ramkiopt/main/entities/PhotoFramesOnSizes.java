package com.ramkiopt.main.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photo_frames_on_sizes", schema = "ramki_opt", catalog = "")
public class PhotoFramesOnSizes {
    private long id;
    private long photoFrameId;
    private long sizeId;
    private PhotoFrames photoFramesByPhotoFrameId;
    private Sizes sizesBySizeId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "photo_frame_id", nullable = false)
    public long getPhotoFrameId() {
        return photoFrameId;
    }

    public void setPhotoFrameId(long photoFrameId) {
        this.photoFrameId = photoFrameId;
    }

    @Basic
    @Column(name = "size_id", nullable = false)
    public long getSizeId() {
        return sizeId;
    }

    public void setSizeId(long sizeId) {
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
    @JoinColumn(name = "photo_frame_id", referencedColumnName = "id", nullable = false)
    public PhotoFrames getPhotoFramesByPhotoFrameId() {
        return photoFramesByPhotoFrameId;
    }

    public void setPhotoFramesByPhotoFrameId(PhotoFrames photoFramesByPhotoFrameId) {
        this.photoFramesByPhotoFrameId = photoFramesByPhotoFrameId;
    }

    @ManyToOne
    @JoinColumn(name = "size_id", referencedColumnName = "id", nullable = false)
    public Sizes getSizesBySizeId() {
        return sizesBySizeId;
    }

    public void setSizesBySizeId(Sizes sizesBySizeId) {
        this.sizesBySizeId = sizesBySizeId;
    }
}
