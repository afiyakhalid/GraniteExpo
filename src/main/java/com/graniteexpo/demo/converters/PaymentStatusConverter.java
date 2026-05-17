package com.graniteexpo.demo.converters;

import com.graniteexpo.demo.enums.PaymentStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {

    @Override
    public String convertToDatabaseColumn(PaymentStatus attribute) {
        if (attribute == null) return null;
        // CREATED -> "created"
        return attribute.name().toLowerCase();
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        // "succeeded" -> SUCCEEDED
        return PaymentStatus.valueOf(dbData.toUpperCase());
    }
}