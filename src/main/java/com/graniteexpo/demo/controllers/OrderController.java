package com.graniteexpo.demo.controllers;

import com.graniteexpo.demo.dtos.OrderResponseDTO;
import com.graniteexpo.demo.dtos.OrderResponseDTO;
import com.graniteexpo.demo.dtos.ReserveBlockRequestDTO;
import com.graniteexpo.demo.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createDraft(@PathVariable UUID buyerId) {
        return ResponseEntity.ok(orderService.createDraftOrder(buyerId));
    }
    //add items in the block to the order
    @PostMapping("/{orderId}/items")
    public ResponseEntity<?> reserve(@PathVariable UUID orderId,
                                     @Valid @RequestBody ReserveBlockRequestDTO dto) {
        orderService.reserveBlock(orderId, dto.getBlockId());
        return ResponseEntity.ok(Map.of("status", "reserved"));
    }
    //view items in the cart
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrder(@PathVariable UUID orderId) {
        return ResponseEntity.ok(orderService.getOrder(orderId));
    }
    //remove items from block
    // 1. Remove Item (DELETE)
    @DeleteMapping("/{orderId}/items/{blockId}")
    public ResponseEntity<?> removeBlock(@PathVariable UUID orderId,
                                         @PathVariable UUID blockId) {
        orderService.removeBlock(orderId, blockId);
        return ResponseEntity.ok(Map.of("status", "removed"));
    }

    // 2. Checkout / Confirm (POST)
    @PostMapping("/{orderId}/confirm")
    public ResponseEntity<?> confirmOrder(@PathVariable UUID orderId) {
        orderService.confirmOrder(orderId);
        return ResponseEntity.ok(Map.of("status", "confirmed"));
    }
}