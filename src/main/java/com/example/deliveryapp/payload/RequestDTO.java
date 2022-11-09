package com.example.deliveryapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestDTO {

    @NotNull
    private Long productId;

    @NotNull
    private Long placeId;
}