package com.ramkiopt.main.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Discounts {
    private Long id;
    private Integer percentCount;
    private Timestamp startDate;
    private Timestamp endDate;
    private Long photoFrameId;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "percent_count")
    public Integer getPercentCount() {
        return percentCount;
    }

    public void setPercentCount(Integer percentCount) {
        this.percentCount = percentCount;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "photo_frame_id")
    public Long getPhotoFrameId() {
        return photoFrameId;
    }

    public void setPhotoFrameId(Long photoFrameId) {
        this.photoFrameId = photoFrameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discounts discounts = (Discounts) o;
        return Objects.equals(id, discounts.id) &&
                Objects.equals(percentCount, discounts.percentCount) &&
                Objects.equals(startDate, discounts.startDate) &&
                Objects.equals(endDate, discounts.endDate) &&
                Objects.equals(photoFrameId, discounts.photoFrameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, percentCount, startDate, endDate, photoFrameId);
    }
}
