package com.example.deliveryapp.service.carrier;

import com.example.deliveryapp.model.Carrier;
import com.example.deliveryapp.model.Region;
import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.LoginDTO;
import com.example.deliveryapp.payload.RegisterDTO;
import com.example.deliveryapp.payload.responce.ICarrier;
import com.example.deliveryapp.repository.CarrierRepository;
import com.example.deliveryapp.service.region.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.deliveryapp.constants.MessageKey.*;

@Service
@Slf4j
public class CarrierServiceImpl implements CarrierService{

    private final CarrierRepository carrierRepository;

    private final RegionService regionService;

    public CarrierServiceImpl(CarrierRepository carrierRepository, RegionService regionService) {
        this.carrierRepository = carrierRepository;
        this.regionService = regionService;
    }

    @Override
    public ApiResult<?> registerCarrier(RegisterDTO registerDTO) {
        if (carrierRepository.existsByUserName(registerDTO.getUsername()))
            return ApiResult.errorResponse(ALREADY_ADDED);
        List<Region> regionList = regionService.getRegionsById(registerDTO.getRegionIds());

        Carrier user = new Carrier(
                registerDTO.getUsername(),
                registerDTO.getFullName(),
                registerDTO.getPassword(),
                registerDTO.getPhoneNumber(),
                regionList
        );
        carrierRepository.save(user);
        return ApiResult.successResponse(regionList);
    }

    @Override
    public ApiResult<?> login(LoginDTO loginDTO){
        Optional<Carrier> carrierOption = carrierRepository.findByUserNameAndPassword(loginDTO.getUserName(), loginDTO.getPassword());
        if(carrierOption.isPresent()){
            return  ApiResult.successResponse(SUCCESSFULLY);
        }
        return ApiResult.errorResponse(LOGIN_ERROR);
    }

    @Override
    public ApiResult<?> getCarriersForRegion(String name) {
        List<ICarrier> carriers = carrierRepository.findAllByRegionName(name);
        return ApiResult.successResponse(carriers);
    }
}
