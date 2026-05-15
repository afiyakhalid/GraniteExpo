package com.graniteexpo.demo.controllers;

import com.graniteexpo.demo.dtos.BlockResponseDTO;
import com.graniteexpo.demo.dtos.CreateBlockRequestDTO;
import com.graniteexpo.demo.services.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/blocks")
public class BlockController {

    private final InventoryService inventoryService;

    public BlockController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateBlockRequestDTO dto) {
        UUID id = inventoryService.createBlock(
                dto.getBlockCode(),
                dto.getVendorId(),
                dto.getGraniteId(),
                dto.getQuarryId()
        );
        return ResponseEntity.ok(Map.of("id", id));
    }

    @GetMapping
    public ResponseEntity<List<BlockResponseDTO>> list() {
        return ResponseEntity.ok(inventoryService.listBlocks());
    }

    @GetMapping("/{blockId}")
    public ResponseEntity<BlockResponseDTO> get(@PathVariable UUID blockId) {
        return ResponseEntity.ok(inventoryService.getBlock(blockId));
    }
}