package com.ramkiopt.main.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Sizes {
    private Long id;
    private String format;
    private Integer width;
    private Integer height;
    private Collection<PhotoFramesCommon> photoFramesCommonsById;

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
    @Column(name = "format")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Basic
    @Column(name = "width")
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Basic
    @Column(name = "height")
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sizes sizes = (Sizes) o;
        return Objects.equals(id, sizes.id) &&
                Objects.equals(format, sizes.format) &&
                Objects.equals(width, sizes.width) &&
                Objects.equals(height, sizes.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, format, width, height);
    }

    @OneToMany(mappedBy = "sizesBySizeId")
    public Collection<PhotoFramesCommon> getPhotoFramesCommonsById() {
        return photoFramesCommonsById;
    }

    public void setPhotoFramesCommonsById(Collection<PhotoFramesCommon> photoFramesCommonsById) {
        this.photoFramesCommonsById = photoFramesCommonsById;
    }
}
