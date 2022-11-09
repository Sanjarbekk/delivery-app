package com.example.deliveryapp.controller;

import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.service.region.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.example.deliveryapp.constants.ApiConstant.*;


@RestController
@RequestMapping(value = API_V1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RegionController {

    private RegionService regionService;

    @PostMapping(REGION + ADD)
    public ApiResult<?> add(@RequestParam String name) {
        ApiResult<?> apiResult = regionService.add(name);
        return ApiResult.successResponse(apiResult);
    }
}
