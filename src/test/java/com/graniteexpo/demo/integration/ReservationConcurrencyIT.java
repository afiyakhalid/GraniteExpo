package com.graniteexpo.demo.integration;
import com.graniteexpo.demo.dtos.CreateBlockRequestDTO;
import com.graniteexpo.demo.dtos.OrderResponseDTO;
import com.graniteexpo.demo.entities.*;
import com.graniteexpo.demo.repositories.*;
import com.graniteexpo.demo.services.InventoryService;
import com.graniteexpo.demo.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class ReservationConcurrencyIT {
    @Autowired InventoryService inventoryService;
    @Autowired OrderService orderService;
    @Autowired OrderItemRepo orderItemRepository;

    // Repos needed to create Parent Data safely
    @Autowired VendorRepo vendorRepo;
    @Autowired GraniteRepo graniteRepo;
    @Autowired QuarryRepo quarryRepo;
    @Test
    void onlyOneReservationWins_AmongMultipleBuyers() throws Exception {
        // --- STEP 1: SAFE DATA SETUP (No Hardcoded IDs!) ---
        Vendor v = vendorRepo.save(new Vendor(UUID.randomUUID(), "Race Vendor", "race@test.com"));
        Granite g = graniteRepo.save(new Granite(UUID.randomUUID(), v.getId(), "Race Granite", "race-slug"));
        Quarry q = quarryRepo.save(new Quarry(UUID.randomUUID(), v.getId(), "Race Quarry", "Race State"));

        // Create the "Prize" Block
        CreateBlockRequestDTO blockDto = new CreateBlockRequestDTO();
        blockDto.setBlockCode("PRIZE-" + System.currentTimeMillis());
        blockDto.setVendorId(v.getId());
        blockDto.setGraniteId(g.getId());
        blockDto.setQuarryId(q.getId());

        UUID blockId = inventoryService.createBlock(blockDto.getBlockCode(), blockDto.getVendorId(), blockDto.getGraniteId(), blockDto.getQuarryId());
    }
    //creating 20 users now
    int threadCount = 20;
    List<UUID> orderIds = new ArrayList<>();

    ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    List<Future<UUID>> futures = new ArrayList<>();

    for(int i = 0; i < threadCount; i++) {
        // We pass 'null' for buyerId because your createDraftOrder allows it
        OrderResponseDTO orderDto = orderService.createDraftOrder(null);
        orderIds.add(orderDto.getOrderId());

        }
}
