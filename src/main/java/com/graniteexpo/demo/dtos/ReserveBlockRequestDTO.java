package com.graniteexpo.demo.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public class ReserveBlockRequestDTO {

    @NotNull(message = "Block ID is required")
    private UUID blockId;


    public ReserveBlockRequestDTO() {}


    public ReserveBlockRequestDTO(UUID blockId) {
        this.blockId = blockId;
    }


    public UUID getBlockId() {
        return blockId;
    }


    public void setBlockId(UUID blockId) {
        this.blockId = blockId;
    }
}
