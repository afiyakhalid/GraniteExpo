//package com.graniteexpo.demo.entities;
//import com.graniteexpo.demo.converters.PaymentStatusConverter;
//import com.graniteexpo.demo.enums.PaymentStatus;
//import jakarta.persistence.*;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.JdbcTypeCode;
//import org.hibernate.type.SqlTypes;
//
//import java.math.BigDecimal;
//import java.time.OffsetDateTime;
//import java.util.UUID;
//
//@Entity
//@Table(
//        name = "payments",
//        uniqueConstraints = @UniqueConstraint(
//                name = "uq_payments_provider_ref",
//                columnNames = {"provider", "provider_reference"}
//        )
//)
//
//public class Payment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private UUID id;
//
//    @Column(name = "order_id", nullable = false, columnDefinition = "uuid")
//    private UUID orderId;
//
//    @Column(nullable = false)
//    private String provider;
//
//    @Column(name = "provider_reference", nullable = false)
//    private String providerReference;
//
//    @Column(nullable = false)
//    private BigDecimal amount;
//
//    @Column(nullable = false)
//    private String currency = "USD";
//
////    @Enumerated(EnumType.STRING)
//    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
//    @Column(nullable = false)
//    @Convert(converter = PaymentStatusConverter.class)
//    private PaymentStatus status = PaymentStatus.CREATED;
//
//    @CreationTimestamp
//    @Column(name = "created_at", nullable = false, updatable = false)
//    private OffsetDateTime createdAt;
//
//    @Column(name = "confirmed_at")
//    private OffsetDateTime confirmedAt;
//
//    public UUID getId() { return id; }
//
//    public UUID getOrderId() { return orderId; }
//    public void setOrderId(UUID orderId) { this.orderId = orderId; }
//
//    public String getProvider() { return provider; }
//    public void setProvider(String provider) { this.provider = provider; }
//
//    public String getProviderReference() { return providerReference; }
//    public void setProviderReference(String providerReference) { this.providerReference = providerReference; }
//
//    public BigDecimal getAmount() { return amount; }
//    public void setAmount(BigDecimal amount) { this.amount = amount; }
//
//    public String getCurrency() { return currency; }
//    public void setCurrency(String currency) { this.currency = currency; }
//
//    public PaymentStatus getStatus() { return status; }
//    public void setStatus(PaymentStatus status) { this.status = status; }
//
//    public OffsetDateTime getCreatedAt() { return createdAt; }
//
//    public OffsetDateTime getConfirmedAt() { return confirmedAt; }
//    public void setConfirmedAt(OffsetDateTime confirmedAt) { this.confirmedAt = confirmedAt; }
//
//}
package com.graniteexpo.demo.entities;

import com.graniteexpo.demo.converters.PaymentStatusConverter;
import com.graniteexpo.demo.enums.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "payments",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_payments_provider_ref",
                columnNames = {"provider", "provider_reference"}
        )
)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "order_id", nullable = false, columnDefinition = "uuid")
    private UUID orderId;

    @Column(nullable = false)
    private String provider;

    @Column(name = "provider_reference", nullable = false)
    private String providerReference;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String currency = "USD";

    @Convert(converter = PaymentStatusConverter.class)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.CREATED;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "confirmed_at")
    private OffsetDateTime confirmedAt;

    public UUID getId() { return id; }

    public UUID getOrderId() { return orderId; }
    public void setOrderId(UUID orderId) { this.orderId = orderId; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getProviderReference() { return providerReference; }
    public void setProviderReference(String providerReference) { this.providerReference = providerReference; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public OffsetDateTime getCreatedAt() { return createdAt; }

    public OffsetDateTime getConfirmedAt() { return confirmedAt; }
    public void setConfirmedAt(OffsetDateTime confirmedAt) { this.confirmedAt = confirmedAt; }
}