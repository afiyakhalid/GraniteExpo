package com.graniteexpo.demo.repositories;

import com.graniteexpo.demo.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepo extends JpaRepository<Payment, UUID> {
    Optional<Payment> findByProviderAndProviderReference(String provider, String providerReference);
}
