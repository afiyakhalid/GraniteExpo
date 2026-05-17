package com.graniteexpo.demo.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
public class CreatePaymentRequestDTO {
    @NotBlank
    private String provider;

    @NotBlank
    private String providerReference; // idempotency key e.g. "BANK-REF-123"

    @NotNull
    private BigDecimal amount;

    @NotBlank
    private String currency;

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getProviderReference() { return providerReference; }
    public void setProviderReference(String providerReference) { this.providerReference = providerReference; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
}

