package com.robxrocks.simple.merchant.service.db;

import com.robxrocks.simple.merchant.service.entity.Offer;

import java.util.ArrayList;
import java.util.List;

public class MemoryDatastoreImpl implements Datastore {

    private List<Offer> datastore;

    public MemoryDatastoreImpl() {
        this.datastore = new ArrayList<>();
    }

    public List<Offer> getDatastore() {
        return datastore;
    }

    public void addEntity(Offer entity) {
        datastore.add(entity);
    }

}
