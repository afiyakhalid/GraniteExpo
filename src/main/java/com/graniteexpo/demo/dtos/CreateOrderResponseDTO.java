package com.graniteexpo.demo.dtos;
import java.util.UUID;
public class CreateOrderResponseDTO {
    private String blockCode;
    private UUID vendorId;
    private UUID graniteId;
    private UUID quarryId;

    // Getters and Setters
    public String getBlockCode() { return blockCode; }
    public void setBlockCode(String blockCode) { this.blockCode = blockCode; }
    public UUID getVendorId() { return vendorId; }
    public void setVendorId(UUID vendorId) { this.vendorId = vendorId; }
    public UUID getGraniteId() { return graniteId; }
    public void setGraniteId(UUID graniteId) { this.graniteId = graniteId; }
    public UUID getQuarryId() { return quarryId; }
    public void setQuarryId(UUID quarryId) { this.quarryId = quarryId; }
}
