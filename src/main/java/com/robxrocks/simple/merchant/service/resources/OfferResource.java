package com.robxrocks.simple.merchant.service.resources;

import com.robxrocks.simple.merchant.service.api.AddOfferRequest;
import com.robxrocks.simple.merchant.service.dao.OfferDao;
import com.robxrocks.simple.merchant.service.entity.Offer;
import com.robxrocks.simple.merchant.service.services.OfferService;
import io.swagger.annotations.*;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/offers")
@Api(value = "/offers", description = "Simple Merchant Service")
@Produces(APPLICATION_JSON)
@Builder
public class OfferResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferResource.class);

    @NotNull
    private OfferDao offerDao;

    @NotNull
    private OfferService offerService;

    /**
     * Adds an offer.
     *
     * @param request the AddOffer request.
     * @return the response.
     */
    @POST
    @Path("/add-offer")
    @Consumes(APPLICATION_JSON)
    @ApiOperation(value = "Add an offer",
            notes = "Adds an offer.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Valid response - offer added"),
            @ApiResponse(code = 422, message = "Invalid request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Response addOffer(@ApiParam(value = "Add Offer Request", required = true)
                    @Valid final AddOfferRequest request) {

        Offer offer = Offer.builder()
                .description(request.getDescription())
                .price(request.getPrice())
                .currency(request.getCurrency())
                .build();

        offerDao.save(offer);

        final Response response = Response.ok().build();
        LOGGER.info("Simple Merchant Service - Add Offer Response: status {}.", response.getStatus());

        return response;
    }

    /**
     * Gets all offers.
     *
     * @return list of offers.
     */
    @GET
    @ApiOperation(value = "Get all offers",
            notes = "Gets all offers")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Valid response - the offers are returned"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Response getOffers() {

        LOGGER.info("Simple Merchant Service - Get Offers REST API called");

        final Response response = Response.ok(offerService.convertOffers(offerDao.getAll())).build();
        LOGGER.info("Simple Merchant Service - Get Offers Response: status {}.", response.getStatus());

        return response;
    }

}
