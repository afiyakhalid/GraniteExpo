package com.graniteexpo.demo.services.impl;

import com.graniteexpo.demo.dtos.*;
import com.graniteexpo.demo.entities.*;
import com.graniteexpo.demo.enums.BlockStatus;
import com.graniteexpo.demo.enums.OrderStatus;
import com.graniteexpo.demo.exceptions.ConflictException;
import com.graniteexpo.demo.repositories.*;
import com.graniteexpo.demo.services.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepository;
    private final BlockRepo blockRepo;
    private final UserRepo userRepo;

    public OrderServiceImpl(OrderRepo orderRepository,
                            OrderItemRepo orderItemRepository,
                            BlockRepo blockRepo,UserRepo userRepo) {
        this.orderRepo = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.blockRepo = blockRepo;
        this.userRepo = userRepo;
    }

    @Override
    public OrderResponseDTO createDraftOrder(UUID buyerId) {
        Order o = new Order();
        o.setId(UUID.randomUUID());
        o.setOrderNumber("GX-" + System.currentTimeMillis());
        o.setStatus(OrderStatus.draft);
        o.setCreatedAt(OffsetDateTime.now());
        orderRepo.save(o);



        if (buyerId != null) {
            User buyer = userRepo.getReferenceById(buyerId);
            o.setBuyer(buyer);
            orderRepo.save(o);
        }
        return new OrderResponseDTO(
                o.getId(),
                o.getOrderNumber(),
                o.getStatus(),
                BigDecimal.ZERO,
                "USD"
        );
    }




    @Override
    public OrderResponseDTO createOrder(UUID buyerId, UUID blockId) {
        return null;
    }
    @Transactional
    @Override
    public void reserveBlock(UUID orderId, UUID blockId) {
        if (!orderRepo.existsById(orderId)) {
            throw new RuntimeException("Order not found");
        }
        Block block = blockRepo.findById(blockId).orElseThrow(() -> new RuntimeException("Block not found"));
        if (block.getStatus() != BlockStatus.available)
            throw new ConflictException("Block not available: " + block.getStatus());

        try {
            Order orderRef = orderRepo.getReferenceById(orderId);

            OrderItem item = new OrderItem();
            item.setOrder(orderRef);
            item.setBlock(block);
            item.setQuantity(1); // 1 block = 1 item


            // SAVE using the standard method
            orderItemRepository.save(item);
        } catch (
                DataIntegrityViolationException e) {
            throw new ConflictException("Block already reserved/sold (unique constraint hit).");
        }
        block.setStatus(BlockStatus.reserved);
        block.setReservedUntil(OffsetDateTime.now().plus(30, ChronoUnit.MINUTES));
        blockRepo.save(block);
    }

    @Override
    public void removeBlock(UUID orderId, UUID blockId) {

    }

    @Override
    public OrderResponseDTO getOrder(UUID orderId) {
        Order o= orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);
        List<UUID> blockIds = items.stream()
                .map(item -> item.getBlock().getId())
                .collect(Collectors.toList());
        return new OrderResponseDTO(
                o.getId(),
                o.getOrderNumber(),
                o.getStatus(),


                o.getCreatedAt(),
                blockIds

        );
    }

    @Override
    @Transactional
    public void confirmOrder(UUID orderId) {

    }
}
