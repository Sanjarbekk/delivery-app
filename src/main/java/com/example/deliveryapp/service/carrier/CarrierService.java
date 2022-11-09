package com.example.deliveryapp.service.carrier;

import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.LoginDTO;
import com.example.deliveryapp.payload.RegisterDTO;

public interface CarrierService {
    ApiResult<?> registerCarrier(RegisterDTO registerDTO);

    ApiResult<?> login(LoginDTO loginDTO);

    ApiResult<?> getCarriersForRegion(String name);
}
