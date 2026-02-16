package com.graniteexpo.demo.services;
import com.graniteexpo.demo.dtos.OrderResponseDTO;
import com.graniteexpo.demo.dtos.OrderResponseDTO;
import java.util.UUID;
public interface OrderService {
    OrderResponseDTO createDraftOrder(UUID buyerId);

    OrderResponseDTO createOrder(UUID buyerId, UUID blockId);
    void reserveBlock(UUID orderId, UUID blockId);


    void removeBlock(UUID orderId, UUID blockId);


    OrderResponseDTO getOrder(UUID orderId);


    void confirmOrder(UUID orderId);
}
