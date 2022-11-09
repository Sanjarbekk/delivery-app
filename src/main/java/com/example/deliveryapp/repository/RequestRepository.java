package com.example.deliveryapp.repository;


import com.example.deliveryapp.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
