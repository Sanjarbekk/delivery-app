package com.example.deliveryapp.service.transaction;

import com.example.deliveryapp.model.*;
import com.example.deliveryapp.payload.ApiResult;
import com.example.deliveryapp.payload.TransactionDTO;
import com.example.deliveryapp.payload.responce.IPerCarrier;
import com.example.deliveryapp.payload.responce.IRegionsPerNT;
import com.example.deliveryapp.payload.responce.ITPerProduct;
import com.example.deliveryapp.repository.CarrierRepository;
import com.example.deliveryapp.repository.OfferRepository;
import com.example.deliveryapp.repository.RequestRepository;
import com.example.deliveryapp.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.deliveryapp.constants.MessageKey.*;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService{

    private final CarrierRepository carrierRepository;

    private final RequestRepository requestRepository;

    private final OfferRepository offerRepository;

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(CarrierRepository carrierRepository, RequestRepository requestRepository, OfferRepository offerRepository, TransactionRepository transactionRepository) {
        this.carrierRepository = carrierRepository;
        this.requestRepository = requestRepository;
        this.offerRepository = offerRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public ApiResult<?> add(TransactionDTO transactionDTO) {
        Optional<Carrier> optionalCarrier = carrierRepository.findById(transactionDTO.getCarrierId());
        Optional<Request> optionalRequest = requestRepository.findById(transactionDTO.getRequestId());
        Optional<Offer> optionalOffer = offerRepository.findById(transactionDTO.getOfferId());
        if(optionalCarrier.isEmpty() || optionalOffer.isEmpty() || optionalRequest.isEmpty()){
            return  ApiResult.errorResponse(CARRIER_NOT_FOUND +" or " + REQUEST_NOT_FOUND + " or " + OFFER_NOT_FOUND);
        }
        Carrier  carrier = optionalCarrier.get();
        Request  request = optionalRequest.get();
        Offer  offer = optionalOffer.get();
        boolean isRight = checkCondition(carrier, offer, request);
        if(!isRight) {
            return ApiResult.errorResponse(NOT_MERGED);
        }

        Transaction transaction = new Transaction(
                0,
                offer.getPlace().getRegion().getId(),
                carrier,
                offer,
                request
        );
        return ApiResult.successResponse(transactionRepository.save(transaction));
    }


    @Override
    public ApiResult<?> evaluateTransaction(Long id, Integer score) {
        if(score > 10 || score < 1)
            return ApiResult.errorResponse(WRONG_VALUE);
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(transaction.isEmpty()){
            return ApiResult.errorResponse(NOT_FOUND);
        }
        transaction.get().setScore(score);
        transactionRepository.save(transaction.get());
        return ApiResult.successResponse(SUCCESSFULLY);
    }

    @Override
    public ApiResult<?> deliveryRegionsPerNT() {
        List<IRegionsPerNT> result = transactionRepository.getRegionsCount();
        return ApiResult.successResponse(result);
    }

    @Override
    public ApiResult<?> scorePerCarrier(Integer minimumScore) {
        List<IPerCarrier> result = transactionRepository.getAllScore(minimumScore);
        return ApiResult.successResponse(result);
    }

    @Override
    public ApiResult<?> nTPerProduct() {
        List<ITPerProduct> result = transactionRepository.getPerProduct();
        return ApiResult.successResponse(result);
    }

    private boolean checkCondition(Carrier carrier, Offer offer, Request request) {
            Optional<Transaction> optionalTransaction = transactionRepository.findByOfferIdOrRequestId(offer.getId(),
                    request.getId());
            boolean isSameProduct = sameProduct(offer, request);
            boolean canCarrier = checkCarrier(offer, carrier);


        return optionalTransaction.isEmpty() && isSameProduct && canCarrier;
    }

    private boolean checkCarrier(Offer offer, Carrier carrier) {
        Region region = offer.getPlace().getRegion();
        for(Region tmp: carrier.getRegions()){
            if(tmp.equals(region))
                return true;
        }
        return false;
    }

    private boolean sameProduct(Offer offer, Request request) {
        return offer.getPlace().getName() == request.getPlace().getName()
                && offer.getProduct().getName() == request.getProduct().getName();

    }
}
