package com.robxrocks.simple.merchant.service.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Offer {

    private final String description;
    private final Double price;
    private final String currency;

}
