package com.graniteexpo.demo.services;

import com.graniteexpo.demo.dtos.CreateBlockRequestDTO;

import java.util.UUID;

public interface InventoryService  {
    UUID createBlock(CreateBlockRequestDTO dto);

}
