package com.ramkiopt.main.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class DiscountsDto implements Serializable {
    private Long id;
    private Integer percentCount;
    private Timestamp startDate;
    private Timestamp endDate;
    private Long photoFrameId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPercentCount() {
        return percentCount;
    }

    public void setPercentCount(Integer percentCount) {
        this.percentCount = percentCount;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Long getPhotoFrameId() {
        return photoFrameId;
    }

    public void setPhotoFrameId(Long photoFrameId) {
        this.photoFrameId = photoFrameId;
    }
}
