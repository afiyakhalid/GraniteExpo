package com.graniteexpo.demo.dtos;
import com.graniteexpo.demo.enums.OrderStatus; // Make sure you have this Enum created
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
public class OrderResponseDTO {
    private UUID orderId;
    private String orderNumber;


    private OrderStatus status;

    private BigDecimal totalAmount;
    private String currency;
    private OffsetDateTime createdAt;
    private List<UUID> blockIds;
    public OrderResponseDTO(){

    }
    public OrderResponseDTO(UUID orderId, String orderNumber) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        // We can leave the others null for now since it's a draft
    }
    public OrderResponseDTO(UUID orderId, String orderNumber, OrderStatus status, BigDecimal totalAmount, String currency,java.time.OffsetDateTime createdAt, java.util.List<UUID> blockIds) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.blockIds = blockIds;
        this.totalAmount = totalAmount;
        this.currency = currency;
    }

    public OrderResponseDTO(UUID id, String orderNumber, OrderStatus status, OffsetDateTime createdAt, List<UUID> blockIds) {
        this.orderId = id;
        this.orderNumber = orderNumber;
        this.status = status;
        this.createdAt = createdAt;
        this.blockIds = blockIds;
    }

    public OrderResponseDTO(UUID id, String orderNumber, OrderStatus status, BigDecimal zero, String usd) {
        this.orderId = id;
        this.orderNumber = orderNumber;
        this.status = status;
        this.totalAmount = zero;
        this.currency = usd;
    }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
    public List<UUID> getBlockIds() { return blockIds; }
    public void setBlockIds(List<UUID> blockIds) { this.blockIds = blockIds; }
    public UUID getOrderId() { return orderId; }
    public void setOrderId(UUID orderId) { this.orderId = orderId; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}
