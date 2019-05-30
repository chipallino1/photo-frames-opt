package com.ramkiopt.main.dto;

import java.io.Serializable;
import java.util.Objects;

public class ColorsDto implements Serializable {
    private String name;
    private String rgb;

    public ColorsDto(String name, String rgb) {
        this.name = name;
        this.rgb = rgb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRgb() {
        return rgb;
    }

    public void setRgb(String rgb) {
        this.rgb = rgb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColorsDto)) return false;
        ColorsDto colorsDto = (ColorsDto) o;
        return Objects.equals(name, colorsDto.name) &&
                Objects.equals(rgb, colorsDto.rgb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rgb);
    }
}
