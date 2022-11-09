package com.example.deliveryapp.payload.responce;

import com.example.deliveryapp.model.Carrier;

public interface IPerCarrier {

    Carrier getCarrier();

    Integer totalScore();
}
