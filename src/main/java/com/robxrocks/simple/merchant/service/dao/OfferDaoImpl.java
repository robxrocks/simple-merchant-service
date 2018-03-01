package com.robxrocks.simple.merchant.service.dao;

import com.robxrocks.simple.merchant.service.db.Datastore;
import com.robxrocks.simple.merchant.service.entity.Offer;

import java.util.List;

public class OfferDaoImpl implements OfferDao {

    Datastore datastore;

    public OfferDaoImpl(Datastore datastore) {
        this.datastore = datastore;
    }

    public List<Offer> getAll() {
        return datastore.getDatastore();
    }

    public void save(Offer offer) {
        datastore.addEntity(offer);
    }
}
