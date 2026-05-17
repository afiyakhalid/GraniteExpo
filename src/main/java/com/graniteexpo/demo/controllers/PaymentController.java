package com.graniteexpo.demo.controllers;

import com.graniteexpo.demo.dtos.CreatePaymentRequestDTO;
import com.graniteexpo.demo.dtos.PaymentResponseDTO;
import com.graniteexpo.demo.services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders/{orderId}/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@PathVariable UUID orderId,
                                                            @Valid @RequestBody CreatePaymentRequestDTO dto) {
        PaymentResponseDTO res = paymentService.createAndConfirm(
                orderId,
                dto.getProvider(),
                dto.getProviderReference(),
                dto.getAmount(),
                dto.getCurrency()
        );
        return ResponseEntity.ok(res);
    }
}
