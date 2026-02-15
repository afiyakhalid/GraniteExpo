package com.graniteexpo.demo.dtos;
import java.util.UUID;

public class CreateOrderRequestDTO {
    private UUID buyerId;
    private UUID blockId;
    public CreateOrderRequestDTO() {}
    public CreateOrderRequestDTO(UUID buyerId,UUID blockId) {
        this.buyerId = buyerId;
        this.blockId = blockId;
    }
    public UUID getBuyerId() { return buyerId; }
    public void setBuyerId(UUID buyerId) { this.buyerId = buyerId; }

    public UUID getBlockId() { return blockId; }
    public void setBlockId(UUID blockId) { this.blockId = blockId; }
}
