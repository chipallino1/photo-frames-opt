package com.ramkiopt.main.dto;

import java.io.Serializable;

public class PhotoFramesOnColorsDto implements Serializable, Identity {
    private Long id;
    private Long photoFrameId;
    private Long colorId;

    public PhotoFramesOnColorsDto() {
    }

    public PhotoFramesOnColorsDto(Long id, Long photoFrameId, Long colorId) {
        this.id = id;
        this.photoFrameId = photoFrameId;
        this.colorId = colorId;
    }

    @Override
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

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }
}
