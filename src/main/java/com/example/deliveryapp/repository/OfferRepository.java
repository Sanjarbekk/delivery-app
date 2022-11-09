package com.example.deliveryapp.repository;

import com.example.deliveryapp.model.Carrier;
import com.example.deliveryapp.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
