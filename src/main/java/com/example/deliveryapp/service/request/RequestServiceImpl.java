package com.example.deliveryapp.service.request;

import com.example.deliveryapp.model.Place;
import com.example.deliveryapp.model.Product;
import com.example.deliveryapp.model.Request;
import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.RequestDTO;
import com.example.deliveryapp.repository.PlaceRepository;
import com.example.deliveryapp.repository.ProductRepository;
import com.example.deliveryapp.repository.RequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.deliveryapp.constants.MessageKey.PLACE_NOT_FOUND;
import static com.example.deliveryapp.constants.MessageKey.PRODUCT_NOT_FOUND;

@Service
@Slf4j
public class RequestServiceImpl implements RequestService{

    private final PlaceRepository placeRepository;

    private final ProductRepository productRepository;

    private final RequestRepository requestRepository;

    public RequestServiceImpl(PlaceRepository placeRepository, ProductRepository productRepository, RequestRepository requestRepository) {
        this.placeRepository = placeRepository;
        this.productRepository = productRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public ApiResult<?> add(RequestDTO requestDTO) {
        Optional<Place> optionalPlace = placeRepository.findById(requestDTO.getPlaceId());
        Optional<Product> optionalProduct = productRepository.findById(requestDTO.getProductId());

        if(optionalPlace.isEmpty() || optionalProduct.isEmpty()){
            return  ApiResult.errorResponse(PLACE_NOT_FOUND + " or " + PRODUCT_NOT_FOUND);
        }
        Request request = new Request(
                optionalPlace.get(),
                optionalProduct.get()
        );
        return ApiResult.successResponse(requestRepository.save(request));
    }
}
