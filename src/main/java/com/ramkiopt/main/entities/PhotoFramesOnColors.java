package com.ramkiopt.main.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photo_frames_on_colors", schema = "ramki_opt", catalog = "")
public class PhotoFramesOnColors {
    private long id;
    private long photoFrameId;
    private long colorId;
    private PhotoFrames photoFramesByPhotoFrameId;
    private Colors colorsByColorId;

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
    @Column(name = "color_id", nullable = false)
    public long getColorId() {
        return colorId;
    }

    public void setColorId(long colorId) {
        this.colorId = colorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoFramesOnColors that = (PhotoFramesOnColors) o;
        return id == that.id &&
                photoFrameId == that.photoFrameId &&
                colorId == that.colorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoFrameId, colorId);
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
    @JoinColumn(name = "color_id", referencedColumnName = "id", nullable = false)
    public Colors getColorsByColorId() {
        return colorsByColorId;
    }

    public void setColorsByColorId(Colors colorsByColorId) {
        this.colorsByColorId = colorsByColorId;
    }
}
