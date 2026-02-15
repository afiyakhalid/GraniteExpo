package com.graniteexpo.demo.dtos;
import com.graniteexpo.demo.enums.OrderStatus; // Make sure you have this Enum created
import java.math.BigDecimal;
import java.util.UUID;
public class OrderResponseDTO {
    private UUID orderId;
    private String orderNumber;


    private OrderStatus status;


    private BigDecimal totalAmount;
    private String currency;
    public OrderResponseDTO(){

    }
    public OrderResponseDTO(UUID orderId, String orderNumber, OrderStatus status, BigDecimal totalAmount, String currency) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.status = status;
        this.totalAmount = totalAmount;
        this.currency = currency;
    }
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
