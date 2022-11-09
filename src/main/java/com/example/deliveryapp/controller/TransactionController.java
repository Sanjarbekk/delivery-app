package com.example.deliveryapp.controller;

import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.TransactionDTO;
import com.example.deliveryapp.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.example.deliveryapp.constants.ApiConstant.*;

@RestController
@RequestMapping(value = API_V1, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping(TRANSACTION + ADD)
    public ApiResult<?> add(@RequestBody TransactionDTO transactionDTO) {
        ApiResult<?> apiResult = transactionService.add(transactionDTO);
        return ApiResult.successResponse(apiResult);
    }

    @PutMapping(TRANSACTION + "/{id}")
    public ApiResult<?> add( @PathVariable(name = "id") Long id,
                              @RequestParam Integer score) {
        ApiResult<?> apiResult = transactionService.evaluateTransaction(id, score);
        return ApiResult.successResponse(apiResult);
    }

    @GetMapping(TRANSACTION)
    public ApiResult<?> deliveryRegionsPerNT() {
        ApiResult<?> apiResult = transactionService.deliveryRegionsPerNT();
        return ApiResult.successResponse(apiResult);
    }

    @GetMapping(TRANSACTION + DELIVERY)
    public ApiResult<?> scorePerCarrier(@RequestParam Integer minimumScore) {
        ApiResult<?> apiResult = transactionService.scorePerCarrier(minimumScore);
        return ApiResult.successResponse(apiResult);
    }

    @GetMapping(TRANSACTION + PRODUCT)
    public ApiResult<?> nTPerProduct() {
        ApiResult<?> apiResult = transactionService.nTPerProduct();
        return ApiResult.successResponse(apiResult);
    }



}