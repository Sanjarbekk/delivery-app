package com.example.deliveryapp.service.offer;

import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.RequestDTO;

public interface OfferService {
    ApiResult<?> add(RequestDTO requestDTO);
}
