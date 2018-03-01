package com.robxrocks.simple.merchant.service.api;

import com.robxrocks.simple.merchant.service.entity.Offer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class OfferResponse {

    private Offer offer;
}
