package com.ramkiopt.main.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Sizes {
    private long id;
    private String format;
    private Integer width;
    private Integer height;
    private Collection<PhotoFramesOnSizes> photoFramesOnSizesById;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "format", nullable = true, length = 300)
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Basic
    @Column(name = "width", nullable = true)
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Basic
    @Column(name = "height", nullable = true)
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
        return id == sizes.id &&
                Objects.equals(format, sizes.format) &&
                Objects.equals(width, sizes.width) &&
                Objects.equals(height, sizes.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, format, width, height);
    }

    @OneToMany(mappedBy = "sizesBySizeId")
    public Collection<PhotoFramesOnSizes> getPhotoFramesOnSizesById() {
        return photoFramesOnSizesById;
    }

    public void setPhotoFramesOnSizesById(Collection<PhotoFramesOnSizes> photoFramesOnSizesById) {
        this.photoFramesOnSizesById = photoFramesOnSizesById;
    }
}
