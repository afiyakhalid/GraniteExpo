package com.graniteexpo.demo.dtos;
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

    public BlockResponseDTO() {

    }

    public BlockResponseDTO(UUID id, BlockStatus status, String blockCode, OffsetDateTime reservedUntil) {
        this.id = UUID.randomUUID();
        this.status = status;
        this.blockCode = blockCode;
        this.reservedUntil = OffsetDateTime.now().plusDays(1);
        this.vendorId = vendorId;
        this.graniteId = graniteId;
        this.quarryId = quarryId;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;

    }
    public UUID getVendorId() { return vendorId; }
    public void setVendorId(UUID vendorId) { this.vendorId = vendorId; }

    public UUID getGraniteId() { return graniteId; }
    public void setGraniteId(UUID graniteId) { this.graniteId = graniteId; }

    public UUID getQuarryId() { return quarryId; }
    public void setQuarryId(UUID quarryId) { this.quarryId = quarryId; }
    public String getBlockCode(){
        return this.blockCode;
    }
    public void setBlockCode(String blockCode){
        this.blockCode = blockCode;
    }
    public BlockStatus getStatus(){
        return this.status;
    }
    public void setStatus(BlockStatus status){
        this.status = status;
    }
    public OffsetDateTime getReservedUntil(){
        return this.reservedUntil;     }
    public void setReservedUntil(OffsetDateTime reservedUntil){
        this.reservedUntil = reservedUntil;
    }
}
