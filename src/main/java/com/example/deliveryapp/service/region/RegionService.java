package com.example.deliveryapp.service.region;

import com.example.deliveryapp.model.Region;
import com.example.deliveryapp.payload.ApiResult;

import java.util.List;
import java.util.Set;

public interface RegionService {
    ApiResult<?> add(String name);

    List<Region> getRegionsById(List<Long> regionIds);
}
