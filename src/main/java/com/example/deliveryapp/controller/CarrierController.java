package com.example.deliveryapp.controller;


import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.LoginDTO;
import com.example.deliveryapp.payload.RegisterDTO;
import com.example.deliveryapp.service.carrier.CarrierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.deliveryapp.constants.ApiConstant.*;

@RestController
@RequestMapping(value = API_V1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CarrierController {
    private CarrierService carrierService;

    @PostMapping(CARRIER + REGISTER)
    public ApiResult<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        ApiResult<?> apiResult = carrierService.registerCarrier(registerDTO);
        return ApiResult.successResponse(apiResult);
    }

    @PostMapping(CARRIER + LOGIN)
    public ApiResult<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        ApiResult<?> apiResult = carrierService.login(loginDTO);
        return ApiResult.successResponse(apiResult);
    }

    @GetMapping(CARRIER + REGION)
    public ApiResult<?> getCarriersForRegion(@RequestParam String name) {
        ApiResult<?> apiResult = carrierService.getCarriersForRegion(name);
        return ApiResult.successResponse(apiResult);
    }
}
