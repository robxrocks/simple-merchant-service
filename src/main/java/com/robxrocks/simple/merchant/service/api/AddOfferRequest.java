package com.robxrocks.simple.merchant.service.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AddOfferRequest {

    @NotNull
    @Size(min = 2, max = 30)
    private final String description;

    @NotNull
    @Max(1000000)
    private final Double price;

    @NotNull
    @Size(min = 3, max = 3, message  = "size must be 3 characters")
    private final String currency;
}
