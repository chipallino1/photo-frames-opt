package com.ramkiopt.main.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photo_frames", schema = "ramki_opt", catalog = "")
public class PhotoFrames {
    private long id;
    private String name;
    private String vendorCode;
    private String borderMaterial;
    private String insideMaterial;
    private Integer borderWidth;
    private Integer thickness;
    private String description;
    private long photoFramesOnColorsId;
    private long photoFramesOnSizesId;
    private long photoFramesOnPhotosId;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 300)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "vendor_code", nullable = true, length = 300)
    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    @Basic
    @Column(name = "border_material", nullable = true, length = 300)
    public String getBorderMaterial() {
        return borderMaterial;
    }

    public void setBorderMaterial(String borderMaterial) {
        this.borderMaterial = borderMaterial;
    }

    @Basic
    @Column(name = "inside_material", nullable = true, length = 300)
    public String getInsideMaterial() {
        return insideMaterial;
    }

    public void setInsideMaterial(String insideMaterial) {
        this.insideMaterial = insideMaterial;
    }

    @Basic
    @Column(name = "border_width", nullable = true)
    public Integer getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
    }

    @Basic
    @Column(name = "thickness", nullable = true)
    public Integer getThickness() {
        return thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 300)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "photo_frames_on_colors_id", nullable = false)
    public long getPhotoFramesOnColorsId() {
        return photoFramesOnColorsId;
    }

    public void setPhotoFramesOnColorsId(long photoFramesOnColorsId) {
        this.photoFramesOnColorsId = photoFramesOnColorsId;
    }

    @Basic
    @Column(name = "photo_frames_on_sizes_id", nullable = false)
    public long getPhotoFramesOnSizesId() {
        return photoFramesOnSizesId;
    }

    public void setPhotoFramesOnSizesId(long photoFramesOnSizesId) {
        this.photoFramesOnSizesId = photoFramesOnSizesId;
    }

    @Basic
    @Column(name = "photo_frames_on_photos_id", nullable = false)
    public long getPhotoFramesOnPhotosId() {
        return photoFramesOnPhotosId;
    }

    public void setPhotoFramesOnPhotosId(long photoFramesOnPhotosId) {
        this.photoFramesOnPhotosId = photoFramesOnPhotosId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoFrames that = (PhotoFrames) o;
        return id == that.id &&
                photoFramesOnColorsId == that.photoFramesOnColorsId &&
                photoFramesOnSizesId == that.photoFramesOnSizesId &&
                photoFramesOnPhotosId == that.photoFramesOnPhotosId &&
                Objects.equals(name, that.name) &&
                Objects.equals(vendorCode, that.vendorCode) &&
                Objects.equals(borderMaterial, that.borderMaterial) &&
                Objects.equals(insideMaterial, that.insideMaterial) &&
                Objects.equals(borderWidth, that.borderWidth) &&
                Objects.equals(thickness, that.thickness) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vendorCode, borderMaterial, insideMaterial, borderWidth, thickness, description, photoFramesOnColorsId, photoFramesOnSizesId, photoFramesOnPhotosId);
    }
}
