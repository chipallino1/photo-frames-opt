package com.ramkiopt.main.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Currency {
    private long id;
    private String name;
    private String icon;
    private Double value;
    private Collection<PhotoFrames> photoFramesById;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "icon", nullable = true, length = 5)
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "value", nullable = true, precision = 0)
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return id == currency.id &&
                Objects.equals(name, currency.name) &&
                Objects.equals(icon, currency.icon) &&
                Objects.equals(value, currency.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, icon, value);
    }

    @OneToMany(mappedBy = "currencyByCurrencyId")
    public Collection<PhotoFrames> getPhotoFramesById() {
        return photoFramesById;
    }

    public void setPhotoFramesById(Collection<PhotoFrames> photoFramesById) {
        this.photoFramesById = photoFramesById;
    }
}
