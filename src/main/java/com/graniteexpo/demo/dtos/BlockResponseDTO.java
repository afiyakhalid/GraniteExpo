package com.graniteexpo.demo.dtos;

import com.graniteexpo.demo.entities.Block;
import com.graniteexpo.demo.enums.BlockStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

public class BlockResponseDTO {
    private UUID id;
    private BlockStatus status;
    private String blockCode;
    private UUID vendorId;
    private UUID graniteId;
    private UUID quarryId;
    private OffsetDateTime reservedUntil;

    public BlockResponseDTO() {}


    public BlockResponseDTO(Block b) {
        this.id = b.getId();
        this.status = b.getStatus();
        this.blockCode = b.getBlockCode();
        this.vendorId = b.getVendorId();
        this.graniteId = b.getGraniteId();
        this.quarryId = b.getQuarryId();
        this.reservedUntil = b.getReservedUntil();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public BlockStatus getStatus() { return status; }
    public void setStatus(BlockStatus status) { this.status = status; }

    public String getBlockCode() { return blockCode; }
    public void setBlockCode(String blockCode) { this.blockCode = blockCode; }

    public UUID getVendorId() { return vendorId; }
    public void setVendorId(UUID vendorId) { this.vendorId = vendorId; }

    public UUID getGraniteId() { return graniteId; }
    public void setGraniteId(UUID graniteId) { this.graniteId = graniteId; }

    public UUID getQuarryId() { return quarryId; }
    public void setQuarryId(UUID quarryId) { this.quarryId = quarryId; }

    public OffsetDateTime getReservedUntil() { return reservedUntil; }
    public void setReservedUntil(OffsetDateTime reservedUntil) { this.reservedUntil = reservedUntil; }
}