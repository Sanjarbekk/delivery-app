package com.example.deliveryapp.service.request;

import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.RequestDTO;

public interface RequestService {
    ApiResult<?> add(RequestDTO requestDTO);
}
