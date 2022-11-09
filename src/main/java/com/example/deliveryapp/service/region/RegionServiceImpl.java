package com.example.deliveryapp.service.region;

import com.example.deliveryapp.model.Region;
import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.repository.RegionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.deliveryapp.constants.MessageKey.ALREADY_ADDED;

@Service
@Slf4j
public class RegionServiceImpl implements RegionService{

    private final RegionRepository regionRepository;


    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public ApiResult<?> add(String name) {
        Optional<Region> optionalRegion = regionRepository.findByName(name);
        if(optionalRegion.isPresent()){
            return  ApiResult.successResponse(ALREADY_ADDED);
        }
        Region region = new Region(name);
        regionRepository.save(region);
        return ApiResult.successResponse(regionRepository.findAllByOrderByName());
    }

    @Override
    public List<Region> getRegionsById(List<Long> regionIds) {
        return regionRepository.findByIdInOrderByName(regionIds);
    }
}
