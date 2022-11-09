package com.example.deliveryapp.service.place;

import com.example.deliveryapp.model.Place;
import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.PlaceDTO;

import java.util.Optional;

public interface PlaceService {

    ApiResult<?> add(PlaceDTO placeDTO);

    Optional<Place> findById(Long placeId);
}
