package com.robxrocks.simple.merchant.service.resources;

import com.robxrocks.simple.merchant.service.api.AddOfferRequest;
import com.robxrocks.simple.merchant.service.api.OfferResponse;
import com.robxrocks.simple.merchant.service.dao.OfferDao;
import com.robxrocks.simple.merchant.service.entity.Offer;
import com.robxrocks.simple.merchant.service.services.OfferService;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class OfferResourceTest {

    private static final OfferDao offerDao = mock(OfferDao.class);
    private static final OfferService offerService = mock(OfferService.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new OfferResource(offerDao,offerService))
            .build();

    @Test
    public void testGetOffers_Success() {
        Offer offer = Offer.builder()
                .description("offerDescription")
                .price(199.99)
                .currency("CHF")
                .build();
        OfferResponse offerResponse = OfferResponse.builder()
                .offer(offer)
                .build();

        List<Offer> offerList = Arrays.asList(offer);
        List<OfferResponse> offerResponseList = Arrays.asList(offerResponse);

        when(offerDao.getAll()).thenReturn(offerList);
        when(offerService.convertOffers(offerList)).thenReturn(offerResponseList);

        Response response = resources.client()
                .target("/offers")
                .request()
                .get();

        assertEquals(200, response.getStatus());
        List<OfferResponse> list = response.readEntity(new GenericType<ArrayList<OfferResponse>>(){});
        assertThat(list.size(), is(1));
        assertThat(list, is(offerResponseList));
        verify(offerService).convertOffers(offerList);
    }

    @Test
    public void testGetOffers_Success_with_EmptyOffersList() {
        List<Offer> offerList = new ArrayList<>();
        List<OfferResponse> offerResponseList = new ArrayList<>();

        when(offerDao.getAll()).thenReturn(offerList);
        when(offerService.convertOffers(offerList)).thenReturn(offerResponseList);

        Response response = resources.client()
                .target("/offers")
                .request()
                .get();

        assertEquals(200, response.getStatus());
        List<OfferResponse> list = response.readEntity(new GenericType<ArrayList<OfferResponse>>(){});
        assertThat(list.size(), is(0));
        assertThat(list, is(offerResponseList));
        verify(offerService).convertOffers(offerList);
    }

    @Test
    public void testAddOffers_Success() {
        AddOfferRequest request = AddOfferRequest.builder()
                .description("offerDescription")
                .price(199.99)
                .currency("CHF")
                .build();

        doNothing().when(offerDao).save(anyObject());

        Response response = resources.client()
                .target("/offers/add-offer")
                .request()
                .post(Entity.json(request));

        assertEquals(200, response.getStatus());
    }

}