package com.graniteexpo.demo.services;

import java.util.UUID;

public interface InventoryService  {
    UUID createBlock(String blockCode , UUID vendorId, UUID graniteId, UUID quarryId);

}
