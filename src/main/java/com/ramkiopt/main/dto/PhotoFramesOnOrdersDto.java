package com.ramkiopt.main.dto;

import com.ramkiopt.main.entities.Identity;

import java.io.Serializable;

public class PhotoFramesOnOrdersDto implements Serializable, Identity {
    private Long id;
    private Long photoFrameId;
    private Integer photoFramesCount;
    private Long orderId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPhotoFrameId() {
        return photoFrameId;
    }

    public void setPhotoFrameId(Long photoFrameId) {
        this.photoFrameId = photoFrameId;
    }

    public Integer getPhotoFramesCount() {
        return photoFramesCount;
    }

    public void setPhotoFramesCount(Integer photoFramesCount) {
        this.photoFramesCount = photoFramesCount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
