package com.ramkiopt.main.dto;

import java.io.Serializable;

public class PhotoFramesCommonDto implements Serializable {
    private Long id;
    private String photoSrc;
    private Integer cost;
    private Long photoFrameId;
    private Long sizeId;
    private Long colorId;
    private SizesDto sizesDto;
    private ColorsDto colorsDto;
    private PhotoFramesDto photoFramesDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoSrc() {
        return photoSrc;
    }

    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
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

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public SizesDto getSizesDto() {
        return sizesDto;
    }

    public void setSizesDto(SizesDto sizesDto) {
        this.sizesDto = sizesDto;
    }

    public ColorsDto getColorsDto() {
        return colorsDto;
    }

    public void setColorsDto(ColorsDto colorsDto) {
        this.colorsDto = colorsDto;
    }

    public PhotoFramesDto getPhotoFramesDto() {
        return photoFramesDto;
    }

    public void setPhotoFramesDto(PhotoFramesDto photoFramesDto) {
        this.photoFramesDto = photoFramesDto;
    }
}
