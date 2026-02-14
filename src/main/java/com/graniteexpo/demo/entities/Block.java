package com.graniteexpo.demo.entities;
import com.graniteexpo.demo.enums.BlockStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;
@Entity
@Table(name = "blocks")

public class Block {


@Id
@Column(columnDefinition = "uuid")
private UUID id;

@Column(name = "vendor_id", nullable = false, columnDefinition = "uuid")
private UUID vendorId;

@Column(name = "granite_id", nullable = false, columnDefinition = "uuid")
private UUID graniteId;

@Column(name = "quarry_id", nullable = false, columnDefinition = "uuid")
private UUID quarryId;

@Column(name = "block_code", nullable = false, unique = true)
private String blockCode;

@Enumerated(EnumType.STRING)
@JdbcTypeCode(SqlTypes.NAMED_ENUM)
@Column(nullable = false)
private BlockStatus status;

@Column(name = "reserved_until")
private OffsetDateTime reservedUntil;

// getters/setters
public UUID getId() { return id; }
public void setId(UUID id) { this.id = id; }

public UUID getVendorId() { return vendorId; }
public void setVendorId(UUID vendorId) { this.vendorId = vendorId; }

public UUID getGraniteId() { return graniteId; }
public void setGraniteId(UUID graniteId) { this.graniteId = graniteId; }

public UUID getQuarryId() { return quarryId; }
public void setQuarryId(UUID quarryId) { this.quarryId = quarryId; }

public String getBlockCode() { return blockCode; }
public void setBlockCode(String blockCode) { this.blockCode = blockCode; }

public BlockStatus getStatus() { return status; }
public void setStatus(BlockStatus status) { this.status = status; }

public OffsetDateTime getReservedUntil() { return reservedUntil; }
public void setReservedUntil(OffsetDateTime reservedUntil) { this.reservedUntil = reservedUntil;}
}