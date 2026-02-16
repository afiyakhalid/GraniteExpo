package com.graniteexpo.demo.services.impl;

import com.graniteexpo.demo.dtos.*;
import com.graniteexpo.demo.entities.*;
import com.graniteexpo.demo.enums.OrderStatus;
import com.graniteexpo.demo.repositories.*;
import com.graniteexpo.demo.services.OrderService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepository;
    private final BlockRepo blockRepo;

    public OrderServiceImpl(OrderRepo orderRepository,
                            OrderItemRepo orderItemRepository,
                            BlockRepo blockRepo) {
        this.orderRepo = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.blockRepo = blockRepo;
    }

    @Override
    public OrderResponseDTO createDraftOrder() {
        Order o = new Order();
        o.setId(UUID.randomUUID());
        o.setOrderNumber("GX-" + System.currentTimeMillis());
        o.setStatus(OrderStatus.draft);
        o.setCreatedAt(OffsetDateTime.now());
        orderRepo.save(o);

        return new OrderResponseDTO(o.getId(), o.getOrderNumber());
    }

    @Override
    public OrderResponseDTO createOrder(UUID buyerId, UUID blockId) {
        return null;
    }

    @Override
    public void reserveBlock(UUID orderId, UUID blockId) {

    }

    @Override
    public void removeBlock(UUID orderId, UUID blockId) {

    }

    @Override
    public OrderResponseDTO getOrder(UUID orderId) {
        return null;
    }

    @Override
    public void confirmOrder(UUID orderId) {

    }
}
