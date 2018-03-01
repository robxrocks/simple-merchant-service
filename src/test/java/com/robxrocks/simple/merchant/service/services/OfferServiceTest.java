package com.robxrocks.simple.merchant.service.services;

import com.robxrocks.simple.merchant.service.api.OfferResponse;
import com.robxrocks.simple.merchant.service.entity.Offer;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class OfferServiceTest {

    private OfferService specUnderTest;

    @Before
    public void setUp() {
        specUnderTest = new OfferService();
    }

    @Test
    public void testConvertOffers_Success() {
        final String OFFER_DESCRIPTION = "test offer";
        Offer offer = Offer.builder().description(OFFER_DESCRIPTION).build();
        List<Offer> testOffers = Arrays.asList(offer);

        List<OfferResponse> offerResponseList = specUnderTest.convertOffers(testOffers);
        assertThat(offerResponseList.size(), is(1));
        assertEquals(offerResponseList.get(0).getOffer().getDescription(), OFFER_DESCRIPTION);
    }
}