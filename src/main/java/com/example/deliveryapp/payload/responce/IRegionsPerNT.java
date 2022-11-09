package com.example.deliveryapp.payload.responce;

import com.example.deliveryapp.model.Region;

import java.util.List;

public interface IRegionsPerNT {
    Integer getTransactionNumber();

    List<Region> getRegions();
}
