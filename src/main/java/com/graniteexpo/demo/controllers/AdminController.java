package com.graniteexpo.demo.controllers;
import com.graniteexpo.demo.dtos.CreateBlockRequestDTO;
import com.graniteexpo.demo.services.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;
@RestController
@RequestMapping("api/v1/admin")

public class AdminController {
private final InventoryService inventoryService;

    public AdminController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/blocks")
    public ResponseEntity<?> createBlock(@Valid @RequestBody CreateBlockRequestDTO dto) {
        UUID id = inventoryService.createBlock(dto.getBlockCode(), dto.getVendorId(),
                dto.getGraniteId(),
                dto.getQuarryId());
        return ResponseEntity.ok(Map.of("blockId", id));
    }

}
