package com.graniteexpo.demo.controllers;

import com.graniteexpo.demo.dtos.CreateQuarryRequestDTO;
import com.graniteexpo.demo.dtos.QuarryResponseDTO;
import com.graniteexpo.demo.entities.Quarry;
import com.graniteexpo.demo.entities.Vendor;
import com.graniteexpo.demo.repositories.QuarryRepo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quarries")
public class QuarryController {

    private final QuarryRepo quarryRepo;

    public QuarryController(QuarryRepo quarryRepo) {
        this.quarryRepo = quarryRepo;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CreateQuarryRequestDTO dto) {
        Quarry q = new Quarry();

        Vendor v = new Vendor();
        v.setId(dto.getVendorId());
        q.setVendor(v);

        q.setName(dto.getName());
        q.setState(dto.getState());
        q.setDistrict(dto.getDistrict());

        quarryRepo.save(q);
        return ResponseEntity.ok(Map.of("id", q.getId()));
    }

    @GetMapping
    public ResponseEntity<List<QuarryResponseDTO>> list() {
        List<QuarryResponseDTO> res = quarryRepo.findAll()
                .stream()
                .map(QuarryResponseDTO::new)
                .toList();
        return ResponseEntity.ok(res);
    }

    @GetMapping("/{quarryId}")
    public ResponseEntity<QuarryResponseDTO> get(@PathVariable UUID quarryId) {
        Quarry q = quarryRepo.findById(quarryId)
                .orElseThrow(() -> new RuntimeException("Quarry not found: " + quarryId));
        return ResponseEntity.ok(new QuarryResponseDTO(q));
    }
}
