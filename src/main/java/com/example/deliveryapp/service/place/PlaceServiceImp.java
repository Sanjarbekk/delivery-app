package com.example.deliveryapp.service.place;

import com.example.deliveryapp.model.Place;
import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.PlaceDTO;
import com.example.deliveryapp.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaceServiceImp implements PlaceService{

    private final PlaceRepository placeRepository;

    public PlaceServiceImp(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public ApiResult<?> add(PlaceDTO placeDTO) {
        return null;
    }

    @Override
    public Optional<Place> findById(Long placeId) {
        return placeRepository.findById(placeId);
    }
}
