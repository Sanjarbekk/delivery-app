package com.example.deliveryapp.controller;


import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.PlaceDTO;
import com.example.deliveryapp.service.place.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.deliveryapp.constants.ApiConstant.*;

@RestController
@RequestMapping(value = API_V1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PlaceController {

    private PlaceService placeService;

    @PostMapping(PLACE + ADD)
    public ApiResult<?> add(@Valid @RequestBody PlaceDTO placeDTO) {
        ApiResult<?> apiResult = placeService.add(placeDTO);
        return ApiResult.successResponse(apiResult);
    }
}