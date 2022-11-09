package com.example.deliveryapp.service.offer;

import com.example.deliveryapp.model.Offer;
import com.example.deliveryapp.model.Place;
import com.example.deliveryapp.model.Product;
import com.example.deliveryapp.model.Request;
import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.RequestDTO;
import com.example.deliveryapp.repository.OfferRepository;
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
public class OfferServiceImpl implements OfferService {

    private final PlaceRepository placeRepository;

    private final ProductRepository productRepository;

    private final OfferRepository offerRepository;

    public OfferServiceImpl(PlaceRepository placeRepository, ProductRepository productRepository, OfferRepository offerRepository) {
        this.placeRepository = placeRepository;
        this.productRepository = productRepository;
        this.offerRepository = offerRepository;
    }
    @Override
    public ApiResult<?> add(RequestDTO requestDTO) {
        Optional<Place> optionalPlace = placeRepository.findById(requestDTO.getPlaceId());
        Optional<Product> optionalProduct = productRepository.findById(requestDTO.getProductId());

        if(optionalPlace.isEmpty() || optionalProduct.isEmpty()){
            return  ApiResult.errorResponse(PLACE_NOT_FOUND + " or " + PRODUCT_NOT_FOUND);
        }
        Offer offer = new Offer(
                optionalPlace.get(),
                optionalProduct.get()
        );
        return ApiResult.successResponse(offerRepository.save(offer));
    }
}
