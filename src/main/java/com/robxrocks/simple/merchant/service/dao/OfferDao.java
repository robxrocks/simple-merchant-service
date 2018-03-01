package com.robxrocks.simple.merchant.service.dao;

import com.robxrocks.simple.merchant.service.entity.Offer;

import java.util.List;

public interface OfferDao {

    List<Offer> getAll();

    void save(Offer offer);
}
