package com.example.deliveryapp.service.transaction;

import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.TransactionDTO;

public interface TransactionService {
    ApiResult<?> add(TransactionDTO transactionDTO);

    ApiResult<?> evaluateTransaction(Long id, Integer score);

    ApiResult<?> deliveryRegionsPerNT();

    ApiResult<?> scorePerCarrier(Integer minimumScore);

    ApiResult<?> nTPerProduct();
}
