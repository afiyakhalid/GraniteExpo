package com.graniteexpo.demo.repositories;
import com.graniteexpo.demo.entities.OrderItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface OrderItemRepo  extends JpaRepository<OrderItem, UUID> {
    long countByBlockId(UUID blockId);
    List<OrderItem> findByOrderId(UUID orderId);


    Optional<OrderItem> findByOrderIdAndBlockId(UUID orderId, UUID blockId);


    boolean existsByOrderIdAndBlockId(UUID orderId, UUID blockId);
    @Modifying
    @Transactional
    @Query(value = """
      insert into order_items (id, order_id, block_id, created_at)
      values (gen_random_uuid(), :orderId, :blockId, now())
      """, nativeQuery = true)
    void insertOrderItem(@Param("orderId") UUID orderId, @Param("blockId") UUID blockId);
}
