package com.ramkiopt.main.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Colors {
    private Long id;
    private String name;
    private String rgb;
    private Collection<PhotoFramesOnColors> photoFramesOnColorsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    @Column(name = "rgb", nullable = true, length = 300)
    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Colors colors = (Colors) o;
        return id == colors.id &&
                Objects.equals(name, colors.name) &&
                Objects.equals(rgb, colors.rgb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rgb);
    }

    @OneToMany(mappedBy = "colorsByColorId")
    public Collection<PhotoFramesOnColors> getPhotoFramesOnColorsById() {
        return photoFramesOnColorsById;
    }

    public void setPhotoFramesOnColorsById(Collection<PhotoFramesOnColors> photoFramesOnColorsById) {
        this.photoFramesOnColorsById = photoFramesOnColorsById;
    }
}
