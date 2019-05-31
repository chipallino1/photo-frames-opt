package com.ramkiopt.main.entities;

import javax.persistence.*;
import java.util.Collection;
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
    private Integer cost;
    private String description;
    private long userId;
    private long currencyId;
    private Users usersByUserId;
    private Currency currencyByCurrencyId;
    private Collection<PhotoFramesOnCarts> photoFramesOnCartsById;
    private Collection<PhotoFramesOnColors> photoFramesOnColorsById;
    private Collection<PhotoFramesOnPhotos> photoFramesOnPhotosById;
    private Collection<PhotoFramesOnSizes> photoFramesOnSizesById;
    private Collection<Photos> photosById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "cost", nullable = true)
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
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
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "currency_id", nullable = false)
    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(long currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoFrames that = (PhotoFrames) o;
        return id == that.id &&
                userId == that.userId &&
                currencyId == that.currencyId &&
                Objects.equals(name, that.name) &&
                Objects.equals(vendorCode, that.vendorCode) &&
                Objects.equals(borderMaterial, that.borderMaterial) &&
                Objects.equals(insideMaterial, that.insideMaterial) &&
                Objects.equals(borderWidth, that.borderWidth) &&
                Objects.equals(thickness, that.thickness) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vendorCode, borderMaterial, insideMaterial, borderWidth, thickness, cost, description, userId, currencyId);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false,
            updatable = false)
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id", nullable = false, insertable = false,
            updatable = false)
    public Currency getCurrencyByCurrencyId() {
        return currencyByCurrencyId;
    }

    public void setCurrencyByCurrencyId(Currency currencyByCurrencyId) {
        this.currencyByCurrencyId = currencyByCurrencyId;
    }

    @OneToMany(mappedBy = "photoFramesByPhotoFrameId")
    public Collection<PhotoFramesOnCarts> getPhotoFramesOnCartsById() {
        return photoFramesOnCartsById;
    }

    public void setPhotoFramesOnCartsById(Collection<PhotoFramesOnCarts> photoFramesOnCartsById) {
        this.photoFramesOnCartsById = photoFramesOnCartsById;
    }

    @OneToMany(mappedBy = "photoFramesByPhotoFrameId")
    public Collection<PhotoFramesOnColors> getPhotoFramesOnColorsById() {
        return photoFramesOnColorsById;
    }

    public void setPhotoFramesOnColorsById(Collection<PhotoFramesOnColors> photoFramesOnColorsById) {
        this.photoFramesOnColorsById = photoFramesOnColorsById;
    }

    @OneToMany(mappedBy = "photoFramesByPhotoFrameId")
    public Collection<PhotoFramesOnPhotos> getPhotoFramesOnPhotosById() {
        return photoFramesOnPhotosById;
    }

    public void setPhotoFramesOnPhotosById(Collection<PhotoFramesOnPhotos> photoFramesOnPhotosById) {
        this.photoFramesOnPhotosById = photoFramesOnPhotosById;
    }

    @OneToMany(mappedBy = "photoFramesByPhotoFrameId")
    public Collection<PhotoFramesOnSizes> getPhotoFramesOnSizesById() {
        return photoFramesOnSizesById;
    }

    public void setPhotoFramesOnSizesById(Collection<PhotoFramesOnSizes> photoFramesOnSizesById) {
        this.photoFramesOnSizesById = photoFramesOnSizesById;
    }

    @OneToMany(mappedBy = "photoFramesByPhotoFrameId")
    public Collection<Photos> getPhotosById() {
        return photosById;
    }

    public void setPhotosById(Collection<Photos> photosById) {
        this.photosById = photosById;
    }
}
