package com.graniteexpo.demo.controllers;

import com.graniteexpo.demo.dtos.CreateGraniteRequestDTO;
import com.graniteexpo.demo.dtos.GraniteResponseDTO;
import com.graniteexpo.demo.entities.Granite;
import com.graniteexpo.demo.entities.Vendor;
import com.graniteexpo.demo.repositories.GraniteRepo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/granites")
public class GraniteController {

    private final GraniteRepo graniteRepo;

    public GraniteController(GraniteRepo graniteRepo) {
        this.graniteRepo = graniteRepo;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateGraniteRequestDTO dto) {
        Granite g = new Granite();
        g.setId(UUID.randomUUID());

        Vendor v = new Vendor();
        v.setId(dto.getVendorId());
        g.setVendor(v);

        g.setName(dto.getName());
        g.setSlug(dto.getSlug());
        g.setDescription(dto.getDescription());
        g.setCreatedAt(OffsetDateTime.now());

        graniteRepo.save(g);
        return ResponseEntity.ok(Map.of("id", g.getId()));
    }

    @GetMapping
    public ResponseEntity<List<GraniteResponseDTO>> list() {
        List<GraniteResponseDTO> res = graniteRepo.findAll()
                .stream()
                .map(GraniteResponseDTO::new)
                .toList();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{graniteId}")
    public ResponseEntity<GraniteResponseDTO> get(@PathVariable UUID graniteId) {
        Granite g = graniteRepo.findById(graniteId)
                .orElseThrow(() -> new RuntimeException("Granite not found: " + graniteId));
        return ResponseEntity.ok(new GraniteResponseDTO(g));
    }
}