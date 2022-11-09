package com.example.deliveryapp.repository;

import com.example.deliveryapp.model.Carrier;
import com.example.deliveryapp.payload.responce.ICarrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {
    boolean existsByUserName(String username);

    Optional<Carrier> findByUserNameAndPassword(String userName, String password);

    @Query(value = "select c.name                       as name        ,\n" +
            "              c.phone_number               as phoneNumber       \n" +
            "       from carrier c join region r where r.name = :name" +
            "order by c.name", nativeQuery = true)
    List<ICarrier> findAllByRegionName(@Param("name")String name);
}
