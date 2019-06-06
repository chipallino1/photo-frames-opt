package com.ramkiopt.main.dto;

import java.io.Serializable;

public class PhotoFramesOnSizesDto implements Serializable {
    private Long id;
    private Long photoFrameId;
    private Long sizeId;

    public PhotoFramesOnSizesDto() {
    }

    public PhotoFramesOnSizesDto(Long id, Long photoFrameId, Long sizeId) {
        this.id = id;
        this.photoFrameId = photoFrameId;
        this.sizeId = sizeId;
    }

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

    public Long getSizeId() {
        return sizeId;
    }

    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }
}
