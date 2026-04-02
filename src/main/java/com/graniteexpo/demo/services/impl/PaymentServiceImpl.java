package com.graniteexpo.demo.services.impl;
import com.graniteexpo.demo.dtos.PaymentResponseDTO;
import com.graniteexpo.demo.entities.Block;
import com.graniteexpo.demo.entities.Order;
import com.graniteexpo.demo.entities.OrderItem;
import com.graniteexpo.demo.entities.Payment;
import com.graniteexpo.demo.enums.BlockStatus;
import com.graniteexpo.demo.enums.OrderStatus;
import com.graniteexpo.demo.enums.PaymentStatus;
import com.graniteexpo.demo.exceptions.ConflictException;
import com.graniteexpo.demo.exceptions.NotFoundException;
import com.graniteexpo.demo.repositories.BlockRepo;
import com.graniteexpo.demo.repositories.OrderItemRepo;
import com.graniteexpo.demo.repositories.OrderRepo;
import com.graniteexpo.demo.repositories.PaymentRepo;
import com.graniteexpo.demo.services.PaymentService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepo paymentRepo;
    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final BlockRepo blockRepo;

    public PaymentServiceImpl(PaymentRepo paymentRepo,
                              OrderRepo orderRepo,
                              OrderItemRepo orderItemRepo,
                              BlockRepo blockRepo) {
        this.paymentRepo = paymentRepo;
        this.orderRepo = orderRepo;
        this.orderItemRepo = orderItemRepo;
        this.blockRepo = blockRepo;
    }
    @Override
    @Transactional
    public PaymentResponseDTO createAndConfirm(UUID orderId,String provider,String providerReference,
                                               BigDecimal amount,
                                               String currency){
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));
        Payment existing = paymentRepo.findByProviderAndProviderReference(provider, providerReference).orElse(null);
        if (existing != null && existing.getStatus() == PaymentStatus.SUCCEEDED) {
            return toDto(existing);
        }

        // 2) Create payment row (unique constraint makes this safe under races)
        Payment p = (existing != null) ? existing : new Payment();
        p.setOrderId(orderId);
        p.setProvider(provider);
        p.setProviderReference(providerReference);
        p.setAmount(amount);
        p.setCurrency(currency);

        p.setStatus(PaymentStatus.SUCCEEDED);
        p.setConfirmedAt(OffsetDateTime.now());

        try {
            p = paymentRepo.save(p);
        } catch (DataIntegrityViolationException e) {

            Payment winner = paymentRepo.findByProviderAndProviderReference(provider, providerReference)
                    .orElseThrow(() -> new ConflictException("Payment idempotency conflict"));
            return toDto(winner);
        }

        // 3) Order transition (idempotent)
        if (order.getStatus() != OrderStatus.draft && order.getStatus() != OrderStatus.pending_payment) {
            throw new ConflictException("Cannot pay order in status: " + order.getStatus());
        }
        order.setStatus(OrderStatus.confirmed);
        orderRepo.save(order);

        // 4) Mark blocks SOLD (idempotent)
        List<OrderItem> items = orderItemRepo.findByOrderId( orderId);
        for (OrderItem item : items) {
            UUID blockId = item.getBlock().getId();
            Block b = blockRepo.findByIdForUpdate(blockId)
                    .orElseThrow(() -> new NotFoundException("Block not found: " + blockId));

            if (b.getStatus() == BlockStatus.sold) continue;

            if (b.getStatus() != BlockStatus.reserved) {
                throw new ConflictException("Block not reserved: " + b.getStatus());
            }

            b.setStatus(BlockStatus.sold);
            b.setReservedUntil(null);
            blockRepo.save(b);
        }

        return toDto(p);
    }

    private PaymentResponseDTO toDto(Payment p) {
        return new PaymentResponseDTO(
                p.getId(),
                p.getOrderId(),
                p.getProvider(),
                p.getProviderReference(),
                p.getAmount(),
                p.getCurrency(),
                p.getStatus()
        );


    }
}
