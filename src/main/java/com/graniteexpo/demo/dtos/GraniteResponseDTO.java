package com.graniteexpo.demo.dtos;

import com.graniteexpo.demo.entities.Granite;

import java.time.OffsetDateTime;
import java.util.UUID;

public class GraniteResponseDTO {
    private UUID id;
    private UUID vendorId;
    private String name;
    private String slug;
    private String description;
    private OffsetDateTime createdAt;

    public GraniteResponseDTO() {}

    public GraniteResponseDTO(Granite g) {
        this.id = g.getId();
        this.vendorId = (g.getVendor() != null) ? g.getVendor().getId() : null;
        this.name = g.getName();
        this.slug = g.getSlug();
        this.description = g.getDescription();
        this.createdAt = g.getCreatedAt();
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getVendorId() { return vendorId; }
    public void setVendorId(UUID vendorId) { this.vendorId = vendorId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}