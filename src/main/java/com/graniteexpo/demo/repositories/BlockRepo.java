package com.graniteexpo.demo.repositories;
import com.graniteexpo.demo.entities.Block;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface BlockRepo extends JpaRepository<Block, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select b from Block b where b.id=:id")
    Optional<Block> findByIdForUpdate(@Param("id") UUID id);

}
