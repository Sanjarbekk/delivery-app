package com.example.deliveryapp.controller;

import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.RequestDTO;
import com.example.deliveryapp.service.region.RegionService;
import com.example.deliveryapp.service.request.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.example.deliveryapp.constants.ApiConstant.*;

@RestController
@RequestMapping(value = API_V1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RequestController {

    private RequestService requestService;

    @PostMapping(REQUEST + ADD)
    public ApiResult<?> add(@RequestBody RequestDTO requestDTO) {
        ApiResult<?> apiResult = requestService.add(requestDTO);
        return ApiResult.successResponse(apiResult);
    }
}