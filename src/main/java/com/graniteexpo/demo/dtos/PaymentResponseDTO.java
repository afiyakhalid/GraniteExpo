package com.graniteexpo.demo.dtos;
import com.graniteexpo.demo.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.UUID;


public class PaymentResponseDTO {
    private UUID paymentId;
    private BigDecimal amount;
    private String provider;
    private String providerReference;
    private PaymentStatus status;
    public PaymentResponseDTO(){}

    public PaymentResponseDTO(UUID paymentId, BigDecimal amount, String provider, String providerReference, PaymentStatus status) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.provider = provider;
        this.providerReference = providerReference;
        this.status = status;
    }

    public PaymentResponseDTO(UUID id, UUID orderId, String provider, String providerReference, BigDecimal amount, String currency, PaymentStatus status) {
    }

    public UUID getPaymentId() { return paymentId; }
    public void setPaymentId(UUID paymentId) { this.paymentId = paymentId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getProviderReference() { return providerReference; }
    public void setProviderReference(String providerReference) { this.providerReference = providerReference; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

}
