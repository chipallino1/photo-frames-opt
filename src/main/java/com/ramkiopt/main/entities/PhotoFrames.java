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
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "photo_frames", schema = "ramki_opt")
public class PhotoFrames {
    private Long id;
    private String name;
    private String vendorCode;
    private String borderMaterial;
    private String insideMaterial;
    private Integer borderWidth;
    private Integer thickness;
    private Integer cost;
    private String description;
    private RowStatus rowStatus;
    private Long userId;
    private Long popularity;
    private Users usersByUserId;
    private Collection<PhotoFramesCommon> photoFramesCommonsById;
    private Collection<PhotoFramesOnCarts> photoFramesOnCartsById;
    private Collection<PhotoFramesOnOrders> photoFramesOnOrdersById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "vendor_code")
    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    @Basic
    @Column(name = "border_material")
    public String getBorderMaterial() {
        return borderMaterial;
    }

    public void setBorderMaterial(String borderMaterial) {
        this.borderMaterial = borderMaterial;
    }

    @Basic
    @Column(name = "inside_material")
    public String getInsideMaterial() {
        return insideMaterial;
    }

    public void setInsideMaterial(String insideMaterial) {
        this.insideMaterial = insideMaterial;
    }

    @Basic
    @Column(name = "border_width")
    public Integer getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
    }

    @Basic
    @Column(name = "thickness")
    public Integer getThickness() {
        return thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "row_status")
    public RowStatus getRowStatus() {
        return rowStatus;
    }

    public void setRowStatus(RowStatus rowStatus) {
        this.rowStatus = rowStatus;
    }

    @Basic
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "popularity")
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
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(vendorCode, that.vendorCode) &&
                Objects.equals(borderMaterial, that.borderMaterial) &&
                Objects.equals(insideMaterial, that.insideMaterial) &&
                Objects.equals(borderWidth, that.borderWidth) &&
                Objects.equals(thickness, that.thickness) &&
                Objects.equals(cost, that.cost) &&
                Objects.equals(description, that.description) &&
                Objects.equals(rowStatus, that.rowStatus) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(popularity, that.popularity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, vendorCode, borderMaterial, insideMaterial, borderWidth, thickness, cost, description, rowStatus, userId, popularity);
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
    public Collection<PhotoFramesCommon> getPhotoFramesCommonsById() {
        return photoFramesCommonsById;
    }

    public void setPhotoFramesCommonsById(Collection<PhotoFramesCommon> photoFramesCommonsById) {
        this.photoFramesCommonsById = photoFramesCommonsById;
    }

    @OneToMany(mappedBy = "photoFramesByPhotoFrameId")
    public Collection<PhotoFramesOnCarts> getPhotoFramesOnCartsById() {
        return photoFramesOnCartsById;
    }

    public void setPhotoFramesOnCartsById(Collection<PhotoFramesOnCarts> photoFramesOnCartsById) {
        this.photoFramesOnCartsById = photoFramesOnCartsById;
    }

    @OneToMany(mappedBy = "photoFramesByPhotoFrameId")
    public Collection<PhotoFramesOnOrders> getPhotoFramesOnOrdersById() {
        return photoFramesOnOrdersById;
    }

    public void setPhotoFramesOnOrdersById(Collection<PhotoFramesOnOrders> photoFramesOnOrdersById) {
        this.photoFramesOnOrdersById = photoFramesOnOrdersById;
    }
}
