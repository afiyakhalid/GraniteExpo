package com.graniteexpo.demo.services;
import com.graniteexpo.demo.dtos.PaymentResponseDTO;

import java.math.BigDecimal;
import java.util.UUID;


public interface PaymentService {
    PaymentResponseDTO createAndConfirm(UUID orderId,
                                        String provider,
                                        String providerReference,
                                        BigDecimal amount,
                                        String currency);


}
