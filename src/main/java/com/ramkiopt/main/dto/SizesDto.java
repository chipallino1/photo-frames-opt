package com.ramkiopt.main.dto;

import java.io.Serializable;
import java.util.Objects;

public class SizesDto implements Serializable, Identity {
    private Long id;
    private String format;
    private Integer width;
    private Integer height;

    public SizesDto() {
    }

    public SizesDto(Long id, String format, Integer width, Integer height) {
        this.id = id;
        this.format = format;
        this.width = width;
        this.height = height;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SizesDto)) return false;
        SizesDto sizesDto = (SizesDto) o;
        return id == sizesDto.id &&
                format.equals(sizesDto.format) &&
                width.equals(sizesDto.width) &&
                height.equals(sizesDto.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, format, width, height);
    }
}
