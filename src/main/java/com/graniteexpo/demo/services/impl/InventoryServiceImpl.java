package com.graniteexpo.demo.services.impl;
import com.graniteexpo.demo.entities.Block;
import com.graniteexpo.demo.enums.BlockStatus;
import com.graniteexpo.demo.repositories.BlockRepo;
import com.graniteexpo.demo.services.InventoryService;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class InventoryServiceImpl  implements InventoryService {
    private final BlockRepo blockrepo;

    public InventoryServiceImpl(BlockRepo blockrepo) {
        this.blockrepo = blockrepo;
    }

    @Override
    public UUID createBlock(String blockCode) {
        Block b = new Block();
        b.setId(UUID.randomUUID());
        b.setBlockCode(blockCode);
        b.setStatus(BlockStatus.AVAILABLE);
        blockrepo.save(b);
        return b.getId();

    }

}
