package com.graniteexpo.demo.dtos;

import com.graniteexpo.demo.entities.Quarry;

import java.time.OffsetDateTime;
import java.util.UUID;

public class QuarryResponseDTO {
    private UUID id;
    private UUID vendorId;
    private String name;
    private String state;
    private String district;
    private OffsetDateTime createdAt;

    public QuarryResponseDTO() {}

    public QuarryResponseDTO(Quarry q) {
        this.id = q.getId();
        this.vendorId = (q.getVendor() != null) ? q.getVendor().getId() : null;
        this.name = q.getName();
        this.state = q.getState();
        this.district = q.getDistrict();
        this.createdAt = q.getCreatedAt();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getVendorId() { return vendorId; }
    public void setVendorId(UUID vendorId) { this.vendorId = vendorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}