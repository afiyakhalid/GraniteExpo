package com.graniteexpo.demo.dtos;
import com.graniteexpo.demo.enums.BlockStatus;

import java.time.OffsetDateTime;
import java.util.UUID;
public class BlockResponseDTO {
    private UUID id;
    private BlockStatus status;
    private String blockCode;
    private OffsetDateTime reservedUntil;

    public BlockResponseDTO() {

    }

    public BlockResponseDTO(UUID id, BlockStatus status, String blockCode, OffsetDateTime reservedUntil) {
        this.id = UUID.randomUUID();
        this.status = status;
        this.blockCode = blockCode;
        this.reservedUntil = OffsetDateTime.now().plusDays(1);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;

    }
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
