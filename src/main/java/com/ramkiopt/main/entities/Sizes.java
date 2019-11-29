package com.ramkiopt.main.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Sizes implements Identity {
    private Long id;
    private String format;
    private Integer width;
    private Integer height;
    private Collection<PhotoFramesOnSizes> photoFramesOnSizesById;

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
    @Column(name = "format", nullable = true, length = 300)
    @Size(min = 1, max = 10)
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Basic
    @Column(name = "width", nullable = true)
    @Min(0)
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Basic
    @Column(name = "height", nullable = true)
    @Min(0)
    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @OneToMany(mappedBy = "sizesBySizeId")
    public Collection<PhotoFramesOnSizes> getPhotoFramesOnSizesById() {
        return photoFramesOnSizesById;
    }

    public void setPhotoFramesOnSizesById(Collection<PhotoFramesOnSizes> photoFramesOnSizesById) {
        this.photoFramesOnSizesById = photoFramesOnSizesById;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Sizes sizes = (Sizes) object;
        return Objects.equals(id, sizes.id) &&
                Objects.equals(format, sizes.format) &&
                Objects.equals(width, sizes.width) &&
                Objects.equals(height, sizes.height) &&
                Objects.equals(photoFramesOnSizesById, sizes.photoFramesOnSizesById);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, format, width, height, photoFramesOnSizesById);
    }
}
