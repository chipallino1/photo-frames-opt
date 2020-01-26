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
public class Colors {
    private Long id;
    private String name;
    private String rgb;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "rgb")
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
        return Objects.equals(id, colors.id) &&
                Objects.equals(name, colors.name) &&
                Objects.equals(rgb, colors.rgb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rgb);
    }

    @OneToMany(mappedBy = "colorsByColorId")
    public Collection<PhotoFramesCommon> getPhotoFramesCommonsById() {
        return photoFramesCommonsById;
    }

    public void setPhotoFramesCommonsById(Collection<PhotoFramesCommon> photoFramesCommonsById) {
        this.photoFramesCommonsById = photoFramesCommonsById;
    }
}
