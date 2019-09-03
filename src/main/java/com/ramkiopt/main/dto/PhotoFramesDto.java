package com.ramkiopt.main.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

public class PhotoFramesDto implements Serializable {
    private Long id;
    private String name;
    private String vendorCode;
    private String borderMaterial;
    private String insideMaterial;
    private Integer borderWidth;
    private Integer thickness;
    private Integer cost;
    private Long userId;
    private String description;
    private List<ColorsDto> colorsDtoList;
    private List<SizesDto> sizesDtoList;

    public PhotoFramesDto(Long id, String name, String vendorCode, String borderMaterial,
                          String insideMaterial, Integer borderWidth, Integer thickness,
                          Integer cost, Long userId, String description) {
        this.id = id;
        this.name = name;
        this.vendorCode = vendorCode;
        this.borderMaterial = borderMaterial;
        this.insideMaterial = insideMaterial;
        this.borderWidth = borderWidth;
        this.thickness = thickness;
        this.cost = cost;
        this.userId = userId;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getBorderMaterial() {
        return borderMaterial;
    }

    public void setBorderMaterial(String borderMaterial) {
        this.borderMaterial = borderMaterial;
    }

    public String getInsideMaterial() {
        return insideMaterial;
    }

    public void setInsideMaterial(String insideMaterial) {
        this.insideMaterial = insideMaterial;
    }

    public Integer getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(Integer borderWidth) {
        this.borderWidth = borderWidth;
    }

    public Integer getThickness() {
        return thickness;
    }

    public void setThickness(Integer thickness) {
        this.thickness = thickness;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @NotNull
    @Size(min = 1, message = "{validation.photoframes.colorsListSize.message}")
    public List<ColorsDto> getColorsDtoList() {
        return colorsDtoList;
    }

    public void setColorsDtoList(List<ColorsDto> colorsDtoList) {
        this.colorsDtoList = colorsDtoList;
    }

    @NotNull
    @Size(min = 1, message = "{validation.photoframes.sizesListSize.message}")
    public List<SizesDto> getSizesDtoList() {
        return sizesDtoList;
    }

    public void setSizesDtoList(List<SizesDto> sizesDtoList) {
        this.sizesDtoList = sizesDtoList;
    }
}
