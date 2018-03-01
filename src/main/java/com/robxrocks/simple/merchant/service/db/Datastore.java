package com.robxrocks.simple.merchant.service.db;

import com.robxrocks.simple.merchant.service.entity.Offer;

import java.util.List;

public interface Datastore {

    List<Offer> getDatastore();

    void addEntity(Offer entity);

}
