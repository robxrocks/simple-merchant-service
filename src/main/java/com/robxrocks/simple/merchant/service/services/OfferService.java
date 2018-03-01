package com.robxrocks.simple.merchant.service.services;

import com.robxrocks.simple.merchant.service.api.OfferResponse;
import com.robxrocks.simple.merchant.service.entity.Offer;

import java.util.ArrayList;
import java.util.List;

public class OfferService {

    /**
     * Converts list of offers to offer resposes
     *
     * @param offers list of offers.
     * @return list of offer responses.
     */
    public List<OfferResponse> convertOffers(List<Offer> offers) {
        List<OfferResponse> response = new ArrayList<>();

        offers.forEach(offer -> response.add(convertOffer(offer)));
        return response;
    }

    private OfferResponse convertOffer(Offer offer) {
        return OfferResponse.builder().offer(offer).build();
    }
}
