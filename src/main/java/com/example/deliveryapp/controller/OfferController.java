package com.example.deliveryapp.controller;

import com.example.deliveryapp.model.Offer;
import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.RequestDTO;
import com.example.deliveryapp.service.offer.OfferService;
import com.example.deliveryapp.service.request.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.deliveryapp.constants.ApiConstant.*;

@RestController
@RequestMapping(value = API_V1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class OfferController {

    private OfferService offerService;

    @PostMapping(OFFER + ADD)
    public ApiResult<?> add(@RequestBody RequestDTO requestDTO) {
        ApiResult<?> apiResult = offerService.add(requestDTO);
        return ApiResult.successResponse(apiResult);
    }
}