package com.example.deliveryapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransactionDTO {

    @NotNull
    private Long carrierId;

    @NotNull
    private Long offerId;

    @NotNull
    private Long requestId;
}
