package com.graniteexpo.demo.repositories;
import com.graniteexpo.demo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface OrderRepo extends JpaRepository<Order,UUID> {
    boolean existsByBlockId(UUID blockId);

    // Or if you really want the count
    long countByBlockId(UUID blockId);

}
