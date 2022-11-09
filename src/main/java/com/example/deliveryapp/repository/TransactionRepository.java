package com.example.deliveryapp.repository;

import com.example.deliveryapp.model.Transaction;
import com.example.deliveryapp.payload.responce.IPerCarrier;
import com.example.deliveryapp.payload.responce.IRegionsPerNT;
import com.example.deliveryapp.payload.responce.ITPerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query(
            value = "SELECT count(*)       as transactionalNumber ,\n" +
                    " (select * from region where region.id = regionId)   as regions \n" +
                    "FROM transaction \n" +
                    "GROUP BY regionId ", nativeQuery = true
    )
    List<IRegionsPerNT> getRegionsCount();

    @Query(
            value = "SELECT * from transaction \n " +
                    "where offer.id == :offerId or request.id = :requestId",
            nativeQuery = true
    )
    Optional<Transaction> findByOfferIdOrRequestId(@Param("offerId") Long offerId, @Param("requestId") Long requestId);

    @Query(
            value = "SELECT sum(score)       as totalScore ,\n" +
                    "carrier            as carrier  \n" +
                    "FROM transaction where score > :minScore \n" +
                    "GROUP BY carrier  " +
                    "ORDER BY name", nativeQuery = true
    )
    List<IPerCarrier> getAllScore(@Param("minScore") Integer minimumScore);

    @Query(
            value = "SELECT count(*)       as transactionCount ,\n" +
                    "product_id            as productId  \n" +
                    "FROM transaction \n" +
                    "GROUP BY 2  " +
                    "ORDER BY 2", nativeQuery = true
    )
    List<ITPerProduct> getPerProduct();
}
