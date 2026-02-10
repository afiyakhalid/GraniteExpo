package com.graniteexpo.demo.entities;
import com.graniteexpo.demo.enums.BlockStatus;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;
@Entity
@Table(name = "blocks")

public class Block {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(name = "block_code", nullable = false, unique = true)
    private String blockCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BlockStatus status;

    @Column(name = "reserved_until")
    private OffsetDateTime reservedUntil;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getBlockCode() { return blockCode; }
    public void setBlockCode(String blockCode) { this.blockCode = blockCode; }

    public BlockStatus getStatus() { return status; }
    public void setStatus(BlockStatus status) { this.status = status; }

    public OffsetDateTime getReservedUntil() { return reservedUntil; }
    public void setReservedUntil(OffsetDateTime reservedUntil) { this.reservedUntil = reservedUntil;}
}
