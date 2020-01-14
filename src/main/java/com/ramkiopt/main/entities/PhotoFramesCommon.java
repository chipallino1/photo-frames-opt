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
@Table(name = "photo_frames_common", schema = "ramki_opt")
public class PhotoFramesCommon implements Identity {
    private Long id;
    private String photoSrc;
    private Integer cost;
    private Long photoFrameId;
    private Long sizeId;
    private Long colorId;
    private PhotoFrames photoFramesByPhotoFrameId;
    private Sizes sizesBySizeId;
    private Colors colorsByColorId;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "photo_src")
    public String getPhotoSrc() {
        return photoSrc;
    }

    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }

    @Basic
    @Column(name = "cost")
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "photo_frame_id")
    public Long getPhotoFrameId() {
        return photoFrameId;
    }

    public void setPhotoFrameId(Long photoFrameId) {
        this.photoFrameId = photoFrameId;
    }

    @Basic
    @Column(name = "size_id")
    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }

    @Basic
    @Column(name = "color_id")
    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoFramesCommon that = (PhotoFramesCommon) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(photoSrc, that.photoSrc) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(photoFrameId, that.photoFrameId) &&
                Objects.equals(sizeId, that.sizeId) &&
                Objects.equals(colorId, that.colorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photoSrc, cost, photoFrameId, sizeId, colorId);
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

    @ManyToOne
    @JoinColumn(name = "color_id", referencedColumnName = "id", nullable = false)
    public Colors getColorsByColorId() {
        return colorsByColorId;
    }

    public void setColorsByColorId(Colors colorsByColorId) {
        this.colorsByColorId = colorsByColorId;
    }
}
