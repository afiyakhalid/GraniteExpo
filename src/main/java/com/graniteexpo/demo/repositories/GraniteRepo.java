package com.graniteexpo.demo.repositories;

import com.graniteexpo.demo.entities.Granite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GraniteRepo extends JpaRepository<Granite, UUID> {
    Optional<Granite> findBySlug(String slug);
}
