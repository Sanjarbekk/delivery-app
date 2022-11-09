package com.example.deliveryapp.repository;

import com.example.deliveryapp.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {
}
