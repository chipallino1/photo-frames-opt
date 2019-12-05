package com.ramkiopt.main.entities;

import com.ramkiopt.main.services.app.base.RowStatus;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "photo_frames", schema = "ramki_opt", catalog = "")
public class PhotoFrames implements Identity {
    private Long id;
    private String name;
    private String vendorCode;
    private String borderMaterial;
    private String insideMaterial;
    private Integer borderWidth;
    private Integer thickness;
    private Integer cost;
    private String description;
    private RowStatus status;
    private Long userId;
    private Users usersByUserId;
    private Collection<PhotoFramesOnCarts> photoFramesOnCartsById;
    private Collection<PhotoFramesOnColors> photoFramesOnColorsById;
    private Collection<PhotoFramesOnSizes> photoFramesOnSizesById;
    private Collection<Photos> photosById;
    private long currencyId;
    private long photoFramesOnColorsId;
    private long photoFramesOnSizesId;
    private long photoFramesOnPhotosId;
    private Collection<PhotoFramesOnOrders> photoFramesOnOrdersById;
    private Collection<Discounts> discountsById;
    private Long popularity;
    private Collection<Orders> ordersById;

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
    @Column(name = "name", nullable = true, length = 300)
    @Size(min = 2, max = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "vendor_code", nullable = true, length = 300)
    @Size(min = 2, max = 100)
    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    @Basic
    @Column(name = "border_material", nullable = true, length = 300)
    @Size(min = 2, max = 100)
    public String getBorderMaterial() {
        return borderMaterial;
    }

    public void setBorderMaterial(String borderMaterial) {
        this.borderMaterial = borderMaterial;
    }

    @Basic
    @Column(name = "inside_material", nullable = true, length = 300)
    @Size(min = 2, max = 100)
    public String getInsideMaterial() {
        return insideMaterial;
    }

    public void setInsideMaterial(String insideMaterial) {
        this.insideMaterial = insideMaterial;
    }

    @Basic
    @Column(name = "border_width", nullable = true)
    @Min(0)
    public Integer getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
    }

    @Basic
    @Column(name = "thickness", nullable = true)
    @Min(0)
    public Integer getThickness() {
        return thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }

    @Basic
    @Column(name = "cost", nullable = true)
    @Min(0)
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 300)
    @Size(max = 300)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    public RowStatus getStatus() {
        return status;
    }

    public void setStatus(RowStatus status) {
        this.status = status;
    }

    public Long getPopularity() {
        return popularity;
    }

    public void setPopularity(Long popularity) {
        this.popularity = popularity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoFrames that = (PhotoFrames) o;
        return id == that.id &&
                userId == that.userId &&
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
        return Objects.hash(id, name, vendorCode, borderMaterial, insideMaterial, borderWidth, thickness, cost,
                description, userId);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Users getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(Users usersByUserId) {
        this.usersByUserId = usersByUserId;
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

    @Basic
    @Column(name = "currency_id", nullable = false)
    public long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    @Basic
    @Column(name = "photo_frames_on_colors_id", nullable = false)
    public long getPhotoFramesOnColorsId() {
        return photoFramesOnColorsId;
    }

    public void setPhotoFramesOnColorsId(Long photoFramesOnColorsId) {
        this.photoFramesOnColorsId = photoFramesOnColorsId;
    }

    @Basic
    @Column(name = "photo_frames_on_sizes_id", nullable = false)
    public long getPhotoFramesOnSizesId() {
        return photoFramesOnSizesId;
    }

    public void setPhotoFramesOnSizesId(Long photoFramesOnSizesId) {
        this.photoFramesOnSizesId = photoFramesOnSizesId;
    }

    @Basic
    @Column(name = "photo_frames_on_photos_id", nullable = false)
    public long getPhotoFramesOnPhotosId() {
        return photoFramesOnPhotosId;
    }

    public void setPhotoFramesOnPhotosId(Long photoFramesOnPhotosId) {
        this.photoFramesOnPhotosId = photoFramesOnPhotosId;
    }

    @OneToMany(mappedBy = "photoFramesByPhotoFrameId")
    public Collection<PhotoFramesOnOrders> getPhotoFramesOnOrdersById() {
        return photoFramesOnOrdersById;
    }

    public void setPhotoFramesOnOrdersById(Collection<PhotoFramesOnOrders> photoFramesOnOrdersById) {
        this.photoFramesOnOrdersById = photoFramesOnOrdersById;
    }

    @OneToMany(mappedBy = "photoFramesByPhotoFrameId")
    public Collection<Discounts> getDiscountsById() {
        return discountsById;
    }

    public void setDiscountsById(Collection<Discounts> discountsById) {
        this.discountsById = discountsById;
    }

    @OneToMany(mappedBy = "photoFramesByPhotoFrameId")
    public Collection<Orders> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<Orders> ordersById) {
        this.ordersById = ordersById;
    }
}
