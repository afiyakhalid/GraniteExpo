package com.graniteexpo.demo.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CreateBlockRequestDTO {

    @NotBlank
    private String blockCode;

    @NotNull
    private UUID vendorId;

    @NotNull
    private UUID graniteId;

    @NotNull
    private UUID quarryId;

    public String getBlockCode() { return blockCode; }
    public void setBlockCode(String blockCode) { this.blockCode = blockCode; }

    public UUID getVendorId() { return vendorId; }
    public void setVendorId(UUID vendorId) { this.vendorId = vendorId; }

    public UUID getGraniteId() { return graniteId; }
    public void setGraniteId(UUID graniteId) { this.graniteId = graniteId; }

    public UUID getQuarryId() { return quarryId; }
    public void setQuarryId(UUID quarryId) { this.quarryId = quarryId; }
}
