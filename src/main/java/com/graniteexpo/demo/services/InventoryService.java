//package com.graniteexpo.demo.services;
//
//import java.util.UUID;
//
//public interface InventoryService  {
//    UUID createBlock(String blockCode , UUID vendorId, UUID graniteId, UUID quarryId);
//
//}
package com.graniteexpo.demo.services;

import com.graniteexpo.demo.dtos.BlockResponseDTO;

import java.util.List;
import java.util.UUID;

public interface InventoryService {
    UUID createBlock(String blockCode, UUID vendorId, UUID graniteId, UUID quarryId);
    List<BlockResponseDTO> listBlocks();
    BlockResponseDTO getBlock(UUID blockId);
}