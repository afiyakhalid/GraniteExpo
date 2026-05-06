package com.graniteexpo.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CreateQuarryRequestDTO {

    @NotNull
    private UUID vendorId;

    @NotBlank
    private String name;

    private String state;
    private String district;

    public UUID getVendorId() { return vendorId; }
    public void setVendorId(UUID vendorId) { this.vendorId = vendorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
}