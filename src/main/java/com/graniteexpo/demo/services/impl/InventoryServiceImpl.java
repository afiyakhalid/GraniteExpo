//package com.graniteexpo.demo.services.impl;
//import com.graniteexpo.demo.dtos.CreateBlockRequestDTO;
//import com.graniteexpo.demo.entities.Block;
//import com.graniteexpo.demo.enums.BlockStatus;
//import com.graniteexpo.demo.repositories.BlockRepo;
//import com.graniteexpo.demo.services.InventoryService;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//@Service
//public class InventoryServiceImpl  implements InventoryService {
//    private final BlockRepo blockrepo;
//
//    public InventoryServiceImpl(BlockRepo blockrepo) {
//        this.blockrepo = blockrepo;
//    }
//
//    @Override
//
//    public UUID createBlock(String blockCode, UUID vendorId, UUID graniteId, UUID quarryId) {
//        Block b = new Block();
//        b.setId(UUID.randomUUID());
//        b.setBlockCode(blockCode);
//
//        b.setVendorId(vendorId);
//        b.setGraniteId(graniteId);
//        b.setQuarryId(quarryId);
//
//        b.setStatus(BlockStatus.available);
//        blockrepo.save(b);
//        return b.getId();
//
//    }
//
//}
package com.graniteexpo.demo.services.impl;

import com.graniteexpo.demo.dtos.BlockResponseDTO;
import com.graniteexpo.demo.entities.Block;
import com.graniteexpo.demo.enums.BlockStatus;
import com.graniteexpo.demo.repositories.BlockRepo;
import com.graniteexpo.demo.services.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final BlockRepo blockRepo;

    public InventoryServiceImpl(BlockRepo blockRepo) {
        this.blockRepo = blockRepo;
    }

    @Override
    public UUID createBlock(String blockCode, UUID vendorId, UUID graniteId, UUID quarryId) {
        Block b = new Block();
        b.setId(UUID.randomUUID()); // because Block has no @GeneratedValue in your entity
        b.setBlockCode(blockCode);
        b.setVendorId(vendorId);
        b.setGraniteId(graniteId);
        b.setQuarryId(quarryId);
        b.setStatus(BlockStatus.available);

        blockRepo.save(b);
        return b.getId();
    }

    @Override
    public List<BlockResponseDTO> listBlocks() {
        return blockRepo.findAll().stream().map(BlockResponseDTO::new).toList();
    }

    @Override
    public BlockResponseDTO getBlock(UUID blockId) {
        Block b = blockRepo.findById(blockId)
                .orElseThrow(() -> new RuntimeException("Block not found: " + blockId));
        return new BlockResponseDTO(b);
    }
}
