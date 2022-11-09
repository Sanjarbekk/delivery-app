package com.example.deliveryapp.repository;

import com.example.deliveryapp.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByName(String name);

    List<Region> findAllByOrderByName();

    List<Region> findByIdInOrderByName(Collection<Long> ids);
}
